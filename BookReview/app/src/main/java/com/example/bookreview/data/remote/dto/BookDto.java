package com.example.bookreview.data.remote.dto;

public class BookDto {
    private String id;
    private String title;
    private String author;
    private String description;
    private String thumbnailUrl;
    private double rating;

    public BookDto() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}