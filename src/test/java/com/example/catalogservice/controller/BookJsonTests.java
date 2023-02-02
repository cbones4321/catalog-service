package com.example.catalogservice.controller;

import com.example.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    JacksonTester<Book> json;

    @Test
    public void test_serialization() throws Exception {
        var book = Book.of("9789295055025", "IPODS Are Monsters", "Apple", 9.99, "Polarsophia");

        var jsonContent = json.write(book);

        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.publisher").isEqualTo(book.publisher());
    }

    @Test
    public void test_deserialization() throws Exception {
        String jsonContent =
                """
                            {
                                "isbn": "9789295055025",
                                "title": "The King and I",
                                "author": "King James",
                                "price": 29.99,
                                "publisher": "Polarsophia"
                            }
                        """;

        assertThat(json.parse(jsonContent))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("9789295055025", "The King and I", "King James", 29.99, "Polarsophia"));
    }
}
