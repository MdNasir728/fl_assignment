package com.example.bookreview.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.bookreview.data.local.LocalDataSource;
import com.example.bookreview.data.local.database.entities.BookEntity;
import com.example.bookreview.data.remote.RemoteDataSource;
import com.example.bookreview.data.remote.dto.BookDto;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.domain.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookRepositoryImpl implements BookRepository {
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;
    private ExecutorService executor;

    public BookRepositoryImpl(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.executor = Executors.newFixedThreadPool(2);
        loadInitialDataIfNeeded();
    }

    @Override
    public LiveData<List<Book>> getAllBooks() {
        MediatorLiveData<List<Book>> result = new MediatorLiveData<>();
        LiveData<List<BookEntity>> localBooks = localDataSource.getAllBooks();

        result.addSource(localBooks, entities -> {
            if (entities != null) {
                result.setValue(mapEntitiesToBooks(entities));
            }
        });

        return result;
    }

    @Override
    public LiveData<Book> getBookById(String id) {
        MediatorLiveData<Book> result = new MediatorLiveData<>();
        LiveData<BookEntity> localBook = localDataSource.getBookById(id);

        result.addSource(localBook, entity -> {
            if (entity != null) {
                result.setValue(mapEntityToBook(entity));
            }
        });

        return result;
    }

    @Override
    public LiveData<List<Book>> getFavoriteBooks() {
        MediatorLiveData<List<Book>> result = new MediatorLiveData<>();
        LiveData<List<BookEntity>> favoriteBooks = localDataSource.getFavoriteBooks();

        result.addSource(favoriteBooks, entities -> {
            if (entities != null) {
                result.setValue(mapEntitiesToBooks(entities));
            }
        });

        return result;
    }

    @Override
    public void saveBook(Book book) {
        BookEntity entity = mapBookToEntity(book);
        localDataSource.insertBook(entity);
    }

    @Override
    public void toggleFavorite(String bookId) {
        executor.execute(() -> {
            // Get current book and toggle its favorite status
            BookEntity currentBook = localDataSource.getBookByIdSync(bookId);
            if (currentBook != null) {
                boolean newFavoriteStatus = !currentBook.isFavorite();
                localDataSource.updateFavoriteStatus(bookId, newFavoriteStatus);
            }
        });
    }

    private void loadInitialDataIfNeeded() {
        executor.execute(() -> {
            // Check if data already exists
            List<BookEntity> existingBooks = localDataSource.getAllBooksSync();

            if (existingBooks == null || existingBooks.isEmpty()) {
                // Only load initial data if database is empty
                loadInitialData();
            }
        });
    }

    private void loadInitialData() {
        List<BookDto> remoteBooksDto = remoteDataSource.getBooks();
        List<BookEntity> bookEntities = new ArrayList<>();

        for (BookDto dto : remoteBooksDto) {
            BookEntity entity = new BookEntity(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getAuthor(),
                    dto.getDescription(),
                    dto.getThumbnailUrl(),
                    dto.getRating(),
                    false // Default favorite status
            );
            bookEntities.add(entity);
        }

        // Use insertBooksIfNotExists to preserve existing data
        localDataSource.insertBooksIfNotExists(bookEntities);
    }

    // Mapping methods
    private List<Book> mapEntitiesToBooks(List<BookEntity> entities) {
        List<Book> books = new ArrayList<>();
        for (BookEntity entity : entities) {
            books.add(mapEntityToBook(entity));
        }
        return books;
    }

    private Book mapEntityToBook(BookEntity entity) {
        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getDescription(),
                entity.getThumbnailUrl(),
                entity.getRating(),
                entity.isFavorite()
        );
    }

    private BookEntity mapBookToEntity(Book book) {
        return new BookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getThumbnailUrl(),
                book.getRating(),
                book.isFavorite()
        );
    }
}