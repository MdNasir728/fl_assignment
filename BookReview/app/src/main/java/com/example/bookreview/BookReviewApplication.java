package com.example.bookreview;

import android.app.Application;
import com.example.bookreview.data.local.LocalDataSource;
import com.example.bookreview.data.local.database.BookDatabase;
import com.example.bookreview.data.remote.RemoteDataSource;
import com.example.bookreview.data.repository.BookRepositoryImpl;
import com.example.bookreview.domain.repository.BookRepository;
import com.example.bookreview.domain.usecase.*;
import com.example.bookreview.presentation.utils.ViewModelFactory;

public class BookReviewApplication extends Application {
    private ViewModelFactory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDependencies();
    }

    private void setupDependencies() {
        // Database
        BookDatabase database = BookDatabase.getInstance(this);

        // Data Sources
        LocalDataSource localDataSource = new LocalDataSource(database.bookDao());
        RemoteDataSource remoteDataSource = new RemoteDataSource(this);

        // Repository
        BookRepository repository = new BookRepositoryImpl(localDataSource, remoteDataSource);

        // Use Cases
        GetBooksUseCase getBooksUseCase = new GetBooksUseCase(repository);
        GetBookDetailsUseCase getBookDetailsUseCase = new GetBookDetailsUseCase(repository);
        SaveBookUseCase saveBookUseCase = new SaveBookUseCase(repository);
        GetFavoriteBooksUseCase getFavoriteBooksUseCase = new GetFavoriteBooksUseCase(repository);

        // ViewModelFactory - now includes repository
        viewModelFactory = new ViewModelFactory(
                getBooksUseCase,
                getBookDetailsUseCase,
                saveBookUseCase,
                getFavoriteBooksUseCase,
                repository
        );
    }

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}