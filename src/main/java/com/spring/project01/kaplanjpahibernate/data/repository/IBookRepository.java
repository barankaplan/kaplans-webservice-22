package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IBookRepository extends CrudRepository<Book, Long> {
    @Query("select b from Book b where b.bookId = ?1")
    public Book findByBookId(Long bookID);

    @Query("select b from Book b where b.bookId = ?1")
    public Optional<Book> findByBookIdv2(Long bookID);

    @Query("select b from Book b where b.bookName = ?1")
    public Optional<Book> findBookByBookNamev2(String bookID);


    public Book findBookByBookName(String bookID);


    @Query("select b from Book b where b.bookId = ?1")
    Optional<Book> findByBookIdTest(Long bookId);
}
