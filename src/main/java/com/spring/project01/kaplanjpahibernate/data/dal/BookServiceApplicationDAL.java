package com.spring.project01.kaplanjpahibernate.data.dal;


import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.repository.IBookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;


@Component
public class BookServiceApplicationDAL {
    private final IBookRepository iBookRepository;

    public BookServiceApplicationDAL(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }


    public Book saveBook(Book book) {
        return doWorkForRepository(() -> iBookRepository.save(book), "CustomerServiceApplicationDAL.saveBook");
    }


    public List<Book> getAllBooks() {
        return (List<Book>) doWorkForRepository(iBookRepository::findAll, "CustomerServiceApplicationDAL.saveBook");
    }

    public Optional<Book> getBookByID(Long bookId) {
        return iBookRepository.findByBookIdv2(bookId);
    }

    public void deleteBookById(Long id) {
        iBookRepository.deleteById(id);

    }

    public Optional<Book> findByBookName(String bookName) {

        return iBookRepository.findBookByBookNamev2(bookName);
    }
}
