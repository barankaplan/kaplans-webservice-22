package com.spring.project01.kaplanjpahibernate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project01.kaplanjpahibernate.KaplanJpaHibernateApplication;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.repository.IBookRepository;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2JpaConfigV2.class }, loader = AnnotationConfigContextLoader.class)
//@Transactional
//@DirtiesContext
//@EnableAutoConfiguration(
////        exclude={DataSourceAutoConfiguration.class}
//)
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerPostgresDBIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private IBookRepository iBookRepository;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenBook_whenBookIsCreated_thenReturnSavedBook() throws Exception {
        Book book = Book.builder().bookName("integration test").genre("integration genre").build();

        //when
        ResultActions perform = mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        //then

        perform.andDo(print()).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.bookName", is(book.getBookName()))).
                andExpect(jsonPath("$.genre", is(book.getGenre())));
    }


    @Test
    void givenBook_whenGetAllBooks_thenReturnBookList
            () throws Exception {
        //given

        Book book = Book.builder().bookName("test").genre("test").build();
        Book book2 = Book.builder().bookName("test2").genre("test2").build();
        List<Book> books = new ArrayList<>();

        books.add(book);
        books.add(book2);

        iBookRepository.saveAll(books);


        //when
        ResultActions perform = mockMvc.perform(get("/api/book"));

        //then

        perform.andDo(print()).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.size()", is(books.size())));


    }

    @Test
    void givenBook_whenGetBookByID_thenReturnBook
            () throws Exception {

        Book book = Book.builder().bookName("test").genre("test").build();

        iBookRepository.save(book);

        //when
        ResultActions perform = mockMvc.perform(get("/api/book/{id}", book.getBookId()));

        //then

        perform.andDo(print()).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.bookId", is((book.getBookId().intValue())))).
                andExpect(jsonPath("$.bookName", is(book.getBookName()))).
                andExpect(jsonPath("$.genre", is(book.getGenre())));
    }

    @Test
    void givenBook_whenGetBookByID_thenReturnEmpty
            () throws Exception {
        //given
        long bookId = 10L;
        Book book = Book.builder().bookName("test").genre("test").build();
        System.out.println(book.getBookId());

        iBookRepository.save(book);


        //when
        ResultActions perform = mockMvc.perform(get("/api/book/{id}", bookId));

        //then

        perform.andDo(print()).
                andExpect(status().isNotFound());
    }


    @Test
    void givenUpdateBook_whenUpdateBook_thenUpdatedBook
            () throws Exception {
        //given
        long bookId = 1L;
        Book book = Book.builder().bookName("test").genre("test").build();
        iBookRepository.save(book);
        Book updatedBook = Book.builder().bookName("updated").genre("updated").build();

        iBookRepository.save(updatedBook);


        //when
        ResultActions perform = mockMvc.perform(put("/api/book/{id}", book.getBookId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)));

        //then

        perform.andDo(print()).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.bookName", is(updatedBook.getBookName()))).
                andExpect(jsonPath("$.genre", is(updatedBook.getGenre())));
        ;
    }

    @Test
    void givenUpdateBook_whenUpdateBook_thenUpdatedBookNegative
            () throws Exception {
        //given
        long bookId = 10L;
        Book book = Book.builder().bookName("test").genre("test").build();
        iBookRepository.save(book);
        Book updatedBook = Book.builder().bookName("updated").genre("updated").build();
        iBookRepository.save(updatedBook);


        //when
        ResultActions perform = mockMvc.perform(put("/api/book/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)));

        //then

        perform.andDo(print()).
                andExpect(status().isNotFound());
        ;
    }

    @Test
    void givenBook_whenDeleteBook_thenReturn200
            () throws Exception {
        //given
        Book book = Book.builder().bookName("test").genre("test").build();
        iBookRepository.save(book);

        //when
        ResultActions perform = mockMvc.perform(delete("/api/book/{id}", book.getBookId()));

        perform.andExpect(status().isOk()).andDo(print());

    }


}
