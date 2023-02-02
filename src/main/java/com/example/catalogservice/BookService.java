package com.example.catalogservice;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.errorhandling.BookAlreadyExistsException;
import com.example.catalogservice.errorhandling.BookNotFoundException;
import com.example.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList(){
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn){
        return this.bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book){
        if(bookRepository.existsByIsbn(book.isbn())){
            throw new BookAlreadyExistsException(book.isbn());
        }

        return this.bookRepository.save(book);
    }

    public Book editBookDetails(Book newBook, String isbn){
        return this.bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var updatedBook = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            newBook.title(),
                            newBook.author(),
                            newBook.price(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.version()
                    );
                    return this.bookRepository.save(updatedBook);
                })
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public void removeBookFromCatalog(String isbn){
        this.bookRepository.delete(isbn);
    }

}
