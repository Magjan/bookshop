package com.polarbookshop.bookshop.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Represents a book in the Polar Bookshop.
 * This class is used to encapsulate the details of a book including its ISBN, title, author, and price.
 */
public record Book(
        @NotBlank(message = "ISBN must not be blank")
        String isbn,
        @NotBlank(message = "Title must not be blank")
        String title,
        @NotBlank(message = "Author must not be blank")
        String author,
        @Positive(message = "Price must be greater than zero")
        double price) {
}

