package com.example.bookreview.presentation.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.domain.usecase.GetBooksUseCase;
import com.example.bookreview.domain.usecase.GetFavoriteBooksUseCase;
import java.util.List;

public class MainViewModel extends ViewModel {
    private GetBooksUseCase getBooksUseCase;
    private GetFavoriteBooksUseCase getFavoriteBooksUseCase;
    private LiveData<List<Book>> books;
    private LiveData<List<Book>> favoriteBooks;

    public MainViewModel(GetBooksUseCase getBooksUseCase, GetFavoriteBooksUseCase getFavoriteBooksUseCase) {
        this.getBooksUseCase = getBooksUseCase;
        this.getFavoriteBooksUseCase = getFavoriteBooksUseCase;
        this.books = getBooksUseCase.execute();
        this.favoriteBooks = getFavoriteBooksUseCase.execute();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public LiveData<List<Book>> getFavoriteBooks() {
        return favoriteBooks;
    }
}