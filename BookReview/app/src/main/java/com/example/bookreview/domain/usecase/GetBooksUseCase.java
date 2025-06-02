package com.example.bookreview.domain.usecase;

import androidx.lifecycle.LiveData;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.domain.repository.BookRepository;
import java.util.List;

public class GetBooksUseCase {
    private BookRepository repository;

    public GetBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Book>> execute() {
        return repository.getAllBooks();
    }
}