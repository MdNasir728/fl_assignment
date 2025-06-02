package com.example.bookreview.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.bookreview.data.local.database.entities.BookEntity;
import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    LiveData<List<BookEntity>> getAllBooks();

    @Query("SELECT * FROM books WHERE id = :id")
    LiveData<BookEntity> getBookById(String id);

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    LiveData<List<BookEntity>> getFavoriteBooks();

    // Add synchronous methods for repository logic
    @Query("SELECT * FROM books")
    List<BookEntity> getAllBooksSync();

    @Query("SELECT * FROM books WHERE id = :id")
    BookEntity getBookByIdSync(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(BookEntity book);

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE id = :id")
    void updateFavoriteStatus(String id, boolean isFavorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(List<BookEntity> books);

    // Add method to insert only if not exists
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBooksIfNotExists(List<BookEntity> books);
}