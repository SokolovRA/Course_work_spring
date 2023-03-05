package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(@Lazy JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Override
    public List<Book> getBooks() {
        return   jdbcTemplate.query("SELECT * FROM book_database ",new BeanPropertyRowMapper<>(Book.class));

    }
    @Override
    public Book getBookByIsbn(String isbn) {
        return jdbcTemplate.query("SELECT * FROM book_database WHERE isbn = ?",
                new Object[]{isbn},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    @Override
    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO book_database VALUES (?,?,?,?)",
                book.getBookName(),book.getBookAuthor(), book.getYearPublication(),book.getIsbn());

    }
    @Override
    public void updateBook(Book book) {
    jdbcTemplate.update("UPDATE book_database SET book_name = ?, book_author = ?, year_publication = ? WHERE isbn = ?",
        book.getBookName(), book.getBookAuthor(), book.getYearPublication(), book.getIsbn());
    }
    @Override
    public void deleteBook(String isbn) {
        jdbcTemplate.update("DELETE FROM book_database WHERE isbn=?",isbn);


    }
}
