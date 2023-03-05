package com.skypro.library.dao;

import com.skypro.library.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooks();
    Book getBookByIsbn(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);
}
