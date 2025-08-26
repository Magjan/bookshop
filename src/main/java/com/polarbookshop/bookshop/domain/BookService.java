package com.polarbookshop.bookshop.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findsByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException(isbn)
        );
    }

    public Book addBook(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void deleteBookByIsbn(String isbn) {
        if (!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBook(String isbn, Book book) {
        if (!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotFoundException(isbn);
        }

        return bookRepository.findsByIsbn(isbn).map(existingBook -> {
            var updateBook = new Book(
                    isbn,
                    book.title() != null ? book.title() : existingBook.title(),
                    book.author() != null ? book.author() : existingBook.author(),
                    book.price() != 0 ? book.price() : existingBook.price()
            );
            return bookRepository.save(updateBook);
        }).orElseGet(() -> addBook(book));

    }


}
