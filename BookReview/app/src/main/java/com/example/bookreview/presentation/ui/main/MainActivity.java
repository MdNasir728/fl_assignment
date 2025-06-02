package com.example.bookreview.presentation.ui.main;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookreview.BookReviewApplication;
import com.example.bookreview.R;
import com.example.bookreview.presentation.utils.ViewModelFactory;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupViewModel();
        observeData();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_books);
        adapter = new BookListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupViewModel() {
        BookReviewApplication app = (BookReviewApplication) getApplication();
        ViewModelFactory factory = app.getViewModelFactory();
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    private void observeData() {
        viewModel.getBooks().observe(this, books -> {
            if (books != null) {
                adapter.updateBooks(books);
            }
        });
    }
}