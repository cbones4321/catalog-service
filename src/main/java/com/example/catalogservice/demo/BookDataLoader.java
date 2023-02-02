package com.example.catalogservice.demo;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addData(){
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.9, "Polarsophia");
        var book2 = Book.of("1234567892", "Cason Bed Time Story", "Dr. Calvin Johnson", 19.99, "Polarsophia");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
