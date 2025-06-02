package com.example.bookreview.data.local;

import androidx.lifecycle.LiveData;
import com.example.bookreview.data.local.database.BookDao;
import com.example.bookreview.data.local.database.entities.BookEntity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalDataSource {
    private BookDao bookDao;
    private ExecutorService executor;

    public LocalDataSource(BookDao bookDao) {
        this.bookDao = bookDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<BookEntity>> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public LiveData<BookEntity> getBookById(String id) {
        return bookDao.getBookById(id);
    }

    public LiveData<List<BookEntity>> getFavoriteBooks() {
        return bookDao.getFavoriteBooks();
    }

    // Add synchronous methods
    public List<BookEntity> getAllBooksSync() {
        return bookDao.getAllBooksSync();
    }

    public BookEntity getBookByIdSync(String id) {
        return bookDao.getBookByIdSync(id);
    }

    public void insertBook(BookEntity book) {
        executor.execute(() -> bookDao.insertBook(book));
    }

    public void insertBooks(List<BookEntity> books) {
        executor.execute(() -> bookDao.insertBooks(books));
    }

    // Add method for inserting without overwriting
    public void insertBooksIfNotExists(List<BookEntity> books) {
        executor.execute(() -> bookDao.insertBooksIfNotExists(books));
    }

    public void updateFavoriteStatus(String id, boolean isFavorite) {
        executor.execute(() -> bookDao.updateFavoriteStatus(id, isFavorite));
    }
}