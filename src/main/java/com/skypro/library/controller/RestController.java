package com.skypro.library.controller;

import com.skypro.library.entity.Book;
import org.springframework.web.bind.annotation.*;
import com.skypro.library.service.BookService;

import java.util.List;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/skypro")
public class RestController {
    private BookService bookService;

    public RestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping ("/api/book")
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @PutMapping ("/api/book")
    public Book updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return book;
    }

    @DeleteMapping("/api/book")
    public String deleteBook(@RequestParam String isbn) {
        bookService.deleteBook(isbn);
        return "Book with isbn = "+isbn+" was delete";
    }
}

