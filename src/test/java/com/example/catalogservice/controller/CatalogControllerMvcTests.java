package com.example.catalogservice.controller;

import com.example.catalogservice.service.BookService;
import com.example.catalogservice.errorhandling.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogController.class)
public class CatalogControllerMvcTests {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void when_searching_for_a_non_existent_book_return_404() throws Exception{
        String isbn = "9789295055025";

        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);

        this.mockMvc.perform(get("/books/" + isbn)).andExpect(status().isNotFound());
    }
}
