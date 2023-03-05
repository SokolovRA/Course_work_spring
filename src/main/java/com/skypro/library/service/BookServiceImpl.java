package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.entity.Book;
import com.skypro.library.exception.BookException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public Book getBookByIsbn(String isbn) {
        Book book = bookDAO.getBookByIsbn(isbn);
        if (book == null) {
            throw new BookException("Book with isbn = " + isbn + " doesn't exist");
        }
        return book;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        validationBook(book);
        bookDAO.addBook(book);

    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Book bookUpdate = this.bookDAO.getBookByIsbn(book.getIsbn());
        if (bookUpdate == null) {
            throw new BookException("Fields are not filled");}
        bookUpdate.setBookName(book.getBookName());
        bookUpdate.setBookAuthor(book.getBookAuthor());
        bookUpdate.setYearPublication(book.getYearPublication());
        validationBook(book);
        bookDAO.updateBook(bookUpdate);

    }
    @Override
    @Transactional
    public void deleteBook(String isbn) {
        Book book = this.bookDAO.getBookByIsbn(isbn);
        if (book == null) {
            throw new BookException("Book with isbn = " + isbn + " doesn't exist ");
        }
        bookDAO.deleteBook(isbn);
    }
    private boolean validationBook(Book book){
        if(
                book.getBookName()==null || book.getBookAuthor()==null ||
                        book.getIsbn()==null || book.getYearPublication()< 0){
            throw new NullPointerException();
        }
        String currentIsbn = book.getIsbn();
        String cleandedIsbn = currentIsbn.replaceAll("[\\-\\s]","");
        if(cleandedIsbn.length() != 13 && !cleandedIsbn.matches("[0-9]+")){
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int i = 0;i< cleandedIsbn.length();i++){
            int digit = Character.getNumericValue(cleandedIsbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = 10 - (sum % 10);
        return  checkDigit == Character.getNumericValue(cleandedIsbn.charAt(cleandedIsbn.length()-1));
    }
}
