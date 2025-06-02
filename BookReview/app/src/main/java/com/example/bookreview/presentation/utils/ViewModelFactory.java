package com.example.bookreview.presentation.utils;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.bookreview.domain.repository.BookRepository;
import com.example.bookreview.domain.usecase.*;
import com.example.bookreview.presentation.ui.detail.BookDetailViewModel;
import com.example.bookreview.presentation.ui.main.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private GetBooksUseCase getBooksUseCase;
    private GetBookDetailsUseCase getBookDetailsUseCase;
    private SaveBookUseCase saveBookUseCase;
    private GetFavoriteBooksUseCase getFavoriteBooksUseCase;
    private BookRepository repository; // Add repository for direct access

    public ViewModelFactory(GetBooksUseCase getBooksUseCase,
                            GetBookDetailsUseCase getBookDetailsUseCase,
                            SaveBookUseCase saveBookUseCase,
                            GetFavoriteBooksUseCase getFavoriteBooksUseCase,
                            BookRepository repository) {
        this.getBooksUseCase = getBooksUseCase;
        this.getBookDetailsUseCase = getBookDetailsUseCase;
        this.saveBookUseCase = saveBookUseCase;
        this.getFavoriteBooksUseCase = getFavoriteBooksUseCase;
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(getBooksUseCase, getFavoriteBooksUseCase);
        } else if (modelClass.isAssignableFrom(BookDetailViewModel.class)) {
            return (T) new BookDetailViewModel(getBookDetailsUseCase, repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}