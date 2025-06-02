package com.example.bookreview.data.remote.api;

import com.example.bookreview.data.remote.dto.BookDto;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("books")
    Call<List<BookDto>> getBooks();
}