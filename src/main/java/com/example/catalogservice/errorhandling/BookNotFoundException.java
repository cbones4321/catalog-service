package com.example.catalogservice.errorhandling;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn){
        super("Book not found with the isbn: " + isbn);
    }
}
