package com.example.bookreview.data.remote;

import android.content.Context;
import com.example.bookreview.data.remote.dto.BookDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoteDataSource {
    private Context context;

    public RemoteDataSource(Context context) {
        this.context = context;
    }

    public List<BookDto> getBooks() {
        try {
            // Reading from assets/books.json for demo
            InputStream is = context.getAssets().open("books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<BookDto>>(){}.getType();
            return gson.fromJson(json, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}