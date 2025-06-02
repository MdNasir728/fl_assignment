package com.example.bookreview.domain.repository;

import androidx.lifecycle.LiveData;
import com.example.bookreview.domain.model.Book;
import java.util.List;

public interface BookRepository {
    LiveData<List<Book>> getAllBooks();
    LiveData<Book> getBookById(String id);
    LiveData<List<Book>> getFavoriteBooks();
    void saveBook(Book book);
    void toggleFavorite(String bookId);
}