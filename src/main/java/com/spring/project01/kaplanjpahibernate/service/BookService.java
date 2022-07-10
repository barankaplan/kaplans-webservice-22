package com.spring.project01.kaplanjpahibernate.service;


import com.spring.project01.kaplanjpahibernate.data.dal.BookServiceApplicationDAL;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Service
public class BookService  {
    private final BookServiceApplicationDAL  bookServiceApplicationDAL;

    public BookService(BookServiceApplicationDAL bookServiceApplicationDAL) {
        this.bookServiceApplicationDAL = bookServiceApplicationDAL;
    }

    public Book saveBook(Book book) {
        return bookServiceApplicationDAL.saveBook(book);
    }

    public List<Book> getAllBooks() {
        return bookServiceApplicationDAL.getAllBooks();  }


    public Optional<Book> getBookByID(Long bookId) {
        return bookServiceApplicationDAL.getBookByID(bookId);
    }

    public void deleteBookById(Long book) {
         bookServiceApplicationDAL.deleteBookById(book);

    }


}
