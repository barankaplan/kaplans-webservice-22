package com.spring.project01.kaplanjpahibernate.service;

import com.spring.project01.kaplanjpahibernate.data.dal.BookServiceApplicationDAL;
import com.spring.project01.kaplanjpahibernate.data.dal.CustomerServiceApplicationDAL;
import com.spring.project01.kaplanjpahibernate.data.entity.BankCard;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import com.spring.project01.kaplanjpahibernate.data.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private BookServiceApplicationDAL bookServiceApplicationDAL;
    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("JUnit test for a method in dal")
    void givenCustomerObject_whenSaveCustomer_thenReturnCustomerObject
            () {
        Book book = new Book();
        book.setBookId(1L);
        book.setGenre("test genre");
        book.setBookName("test book");

        when(bookServiceApplicationDAL.saveBook(Mockito.any(Book.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Book saveBook=bookService.saveBook(book);
        assertThat(saveBook).isNotNull();


    }
}