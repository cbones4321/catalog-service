package com.example.catalogservice.controller;

import com.example.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration ")
public class CatalogServiceE2E {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCreateBook() {
        var newBook = Book.of("9789295055025", "When the Doctor is Patient", "Dr. Liz Jones", 34.90, "Polarsophia");

        webTestClient.post().uri("/books").bodyValue(newBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .value(book -> {
                   assertThat(book).isNotNull();
                   assertThat(book.isbn()).isEqualTo(newBook.isbn());
                });
    }
}
