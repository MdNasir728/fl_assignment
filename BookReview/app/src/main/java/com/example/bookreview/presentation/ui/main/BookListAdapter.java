package com.example.bookreview.presentation.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookreview.R;
import com.example.bookreview.domain.model.Book;
import com.example.bookreview.presentation.ui.detail.BookDetailActivity;
import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private List<Book> books = new ArrayList<>();
    private Context context;

    public BookListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateBooks(List<Book> newBooks) {
        this.books = newBooks;
        notifyDataSetChanged();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, authorText;
        ImageView thumbnailImage, favoriteIcon;

        BookViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tv_title);
            authorText = itemView.findViewById(R.id.tv_author);
            thumbnailImage = itemView.findViewById(R.id.iv_thumbnail);
            favoriteIcon = itemView.findViewById(R.id.iv_favorite);
        }

        void bind(Book book) {
            titleText.setText(book.getTitle());
            authorText.setText(book.getAuthor());

            // Placeholder for image (no external library)
            thumbnailImage.setImageResource(R.drawable.ic_book_placeholder);

            favoriteIcon.setVisibility(book.isFavorite() ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("BOOK_ID", book.getId());
                context.startActivity(intent);
            });
        }
    }
}