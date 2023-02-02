package com.example.catalogservice.domain;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book(
        @Id
        Long id,

        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "Must be correct format")
        @NotBlank(message = "Isbn must be defined")
        String isbn,

        @NotBlank(message = "Title must not be defined")
        String title,

        @NotBlank(message = "Author must be defined")
        String author,

        @Positive(message = "price must be greater than 0")
        @NotNull(message = "Price must be defined")
        Double price,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @Version
        int version
){

        public static Book of(String isbn, String title, String author, Double price){
                return new Book(
                        null, isbn, title, author, price, null, null, 0
                );
        }
}
