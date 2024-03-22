package com.example.bookmngmt;

import com.example.bookmngmt.model.Book;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Hobbit", "J.R.R. Tolkien", "978-0-395-19395-6", 10));
        books.add(new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", "978-0-395-19395-7", 5));
        books.add(new Book(3, "The Silmarillion", "J.R.R. Tolkien", "978-0-395-19395-8", 3));
        return books;
    }

    public static Book getBook() {
        return new Book(1, "The Hobbit", "J.R.R. Tolkien", "978-0-395-19395-6", 10);
    }
}
