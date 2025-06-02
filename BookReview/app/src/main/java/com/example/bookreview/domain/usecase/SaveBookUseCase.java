package com.example.bookreview.domain.usecase;

import com.example.bookreview.domain.model.Book;
import com.example.bookreview.domain.repository.BookRepository;

public class SaveBookUseCase {
    private BookRepository repository;

    public SaveBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public void execute(Book book) {
        repository.saveBook(book);
    }
}