package com.example.bookreview.presentation.ui.detail;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.bookreview.BookReviewApplication;
import com.example.bookreview.R;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.presentation.utils.ViewModelFactory;

public class BookDetailActivity extends AppCompatActivity {
    private BookDetailViewModel viewModel;
    private TextView titleText, authorText, descriptionText;
    private ImageView bookImage;
    private RatingBar ratingBar;
    private Button favoriteButton;
    private String bookId;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookId = getIntent().getStringExtra("BOOK_ID");

        initViews();
        setupViewModel();
        observeData();
        setupClickListeners();
    }

    private void initViews() {
        titleText = findViewById(R.id.tv_detail_title);
        authorText = findViewById(R.id.tv_detail_author);
        descriptionText = findViewById(R.id.tv_description);
        bookImage = findViewById(R.id.iv_detail_image);
        ratingBar = findViewById(R.id.rating_bar);
        favoriteButton = findViewById(R.id.btn_favorite);
    }

    private void setupViewModel() {
        BookReviewApplication app = (BookReviewApplication) getApplication();
        ViewModelFactory factory = app.getViewModelFactory();
        viewModel = new ViewModelProvider(this, factory).get(BookDetailViewModel.class);
    }

    private void observeData() {
        viewModel.getBookDetails(bookId).observe(this, book -> {
            if (book != null) {
                currentBook = book;
                displayBookDetails(book);
            }
        });
    }

    private void displayBookDetails(Book book) {
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        descriptionText.setText(book.getDescription());
        ratingBar.setRating((float) book.getRating());

        // Placeholder for image (no external library)
        bookImage.setImageResource(R.drawable.ic_book_placeholder);

        // Update button text based on favorite status
        updateFavoriteButton(book.isFavorite());
    }

    private void updateFavoriteButton(boolean isFavorite) {
        if (isFavorite) {
            favoriteButton.setText("Remove from Favorites");
            favoriteButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            favoriteButton.setText("Add to Favorites");
            favoriteButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }
    }

    private void setupClickListeners() {
        favoriteButton.setOnClickListener(v -> {
            if (currentBook != null) {
                // Toggle favorite status
                viewModel.toggleFavorite(currentBook.getId());

                // Update UI immediately (the database update will be reflected through LiveData)
                boolean newFavoriteStatus = !currentBook.isFavorite();
                currentBook.setFavorite(newFavoriteStatus);
                updateFavoriteButton(newFavoriteStatus);
            }
        });
    }
}