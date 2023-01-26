package com.example.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookValidationTests {

    private static Validator validator;

    @BeforeEach
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void test_all_fields_created() throws Exception {
        // given
        Book book = new Book(
                "9789295055025",
                "The King is Coming",
                "Dr. Calvin Jones",
                9.99
        );

        // when
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        //then
        assertTrue(constraintViolations.isEmpty());
    }



    @Test
    public void when_title_is_null_validation_fails() throws Exception {
        // given
        Book book = new Book(
                "9789295055025",
                null,
                "Dr. Cason Jones",
                9.99
        );

        // when
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        //then
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void when_isbn_is_incorrect_validation_fails() throws Exception {
        // given
        Book book = new Book(
                "a33",
                "Pizza rolls",
                "Dr. Cason Jones",
                9.99
        );

        // when
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        //then
        assertEquals(1, constraintViolations.size());
        assertEquals(constraintViolations.stream().findFirst().map(ConstraintViolation::getMessage).get(), "Must be correct format");
    }

    @Test
    public void when_author_is_null_validation_fails() throws Exception {
        // given
        Book book = new Book(
                "9789295055025",
                "The King is Coming",
                null,
                9.99
        );

        // when
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        //then
        assertEquals(1, constraintViolations.size());
    }
}
