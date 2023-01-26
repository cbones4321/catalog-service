package com.example.catalogservice.errorhandling;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String isbn){
        super("Book already exists with the specified isbn");
    }
}
