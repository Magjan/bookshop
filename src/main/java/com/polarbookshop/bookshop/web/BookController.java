package com.polarbookshop.bookshop.web;

import com.polarbookshop.bookshop.domain.Book;
import com.polarbookshop.bookshop.domain.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("{isbn}")
    public Book getBookByIsbn(String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIsbn(@PathVariable String isbn) {
        bookService.deleteBookByIsbn(isbn);
    }

    @PutMapping("{isbn}")
    public Book editBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBook(isbn, book);
    }

}
