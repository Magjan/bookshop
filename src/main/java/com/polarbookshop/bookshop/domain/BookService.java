package com.polarbookshop.bookshop.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findsByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException(isbn)
        );
    }


}
