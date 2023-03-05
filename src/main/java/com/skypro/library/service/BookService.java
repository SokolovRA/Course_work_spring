package com.skypro.library.service;

import com.skypro.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookByIsbn(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);
}
