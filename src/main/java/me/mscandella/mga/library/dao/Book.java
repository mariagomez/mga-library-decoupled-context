package me.mscandella.mga.library.dao;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String author;
    @Lob
    private String description;
    private boolean available;
    private String imagePath;

    protected Book() {
    }

    public Book(String name, String author, String description, int rating, boolean available, String imagePath) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.available = available;
        this.imagePath = imagePath;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
