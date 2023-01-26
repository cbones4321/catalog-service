package com.example.catalogservice.repository;

import com.example.catalogservice.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryImpl implements BookRepository{

    Map<String, Book> bookRepository = new ConcurrentHashMap<>();

    @Override
    public List<Book> findAll() {
        return bookRepository.values().stream().toList();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookRepository.get(isbn));
    }

    @Override
    public Boolean existsByIsbn(String isbn) {
        return bookRepository.containsKey(isbn);
    }

    @Override
    public Book save(Book book) {
        this.bookRepository.put(book.isbn(), book);
        return this.bookRepository.get(book.isbn());
    }

    @Override
    public void delete(String isbn) {
        this.bookRepository.remove(isbn);
    }
}
