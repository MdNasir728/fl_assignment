package com.example.bookreview.presentation.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.domain.usecase.GetBookDetailsUseCase;
import com.example.bookreview.domain.repository.BookRepository;

public class BookDetailViewModel extends ViewModel {
    private GetBookDetailsUseCase getBookDetailsUseCase;
    private BookRepository repository;

    public BookDetailViewModel(GetBookDetailsUseCase getBookDetailsUseCase, BookRepository repository) {
        this.getBookDetailsUseCase = getBookDetailsUseCase;
        this.repository = repository;
    }

    public LiveData<Book> getBookDetails(String bookId) {
        return getBookDetailsUseCase.execute(bookId);
    }

    public void toggleFavorite(String bookId) {
        repository.toggleFavorite(bookId);
    }
}