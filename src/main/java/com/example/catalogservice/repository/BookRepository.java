package com.example.catalogservice.repository;

import com.example.catalogservice.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    Boolean existsByIsbn(String isbn);
    Book save(Book book);
    void delete(String isbn);
}
