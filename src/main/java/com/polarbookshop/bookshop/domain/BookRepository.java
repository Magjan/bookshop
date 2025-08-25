package com.polarbookshop.bookshop.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findsByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
