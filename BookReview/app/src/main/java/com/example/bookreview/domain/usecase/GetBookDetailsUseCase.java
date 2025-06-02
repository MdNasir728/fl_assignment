package com.example.bookreview.domain.usecase;

import androidx.lifecycle.LiveData;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.domain.repository.BookRepository;

public class GetBookDetailsUseCase {
    private BookRepository repository;

    public GetBookDetailsUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public LiveData<Book> execute(String bookId) {
        return repository.getBookById(bookId);
    }
}