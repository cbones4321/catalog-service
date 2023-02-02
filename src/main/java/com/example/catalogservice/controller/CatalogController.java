package com.example.catalogservice.controller;

import com.example.catalogservice.service.BookService;
import com.example.catalogservice.domain.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class CatalogController {

    BookService bookService;

    public CatalogController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@Valid @RequestBody Book book){
        return this.bookService.addBookToCatalog(book);
    }

    @GetMapping
    public Iterable<Book> getBookList(){
        return this.bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book searchBooks(@PathVariable("isbn") String isbn){
        return this.bookService.viewBookDetails(isbn);
    }

    @PutMapping("{isbn}")
    public Book updateBook(@Valid @RequestBody Book book, @PathVariable("isbn") String isbn){
        return this.bookService.editBookDetails(book, isbn);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBook(@PathVariable("isbn") String isbn){
        this.bookService.removeBookFromCatalog(isbn);
    }
}
