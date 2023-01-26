package com.example.catalogservice.domain;

import jakarta.validation.constraints.*;

public record Book(
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "Must be correct format")
        @NotBlank(message = "Isbn must be defined")
        String isbn,

        @NotBlank(message = "Title must not be defined")
        String title,

        @NotBlank(message = "Author must be defined")
        String author,

        @Positive(message = "price must be greater than 0")
        @NotNull(message = "Price must be defined")
        Double price
){
}
