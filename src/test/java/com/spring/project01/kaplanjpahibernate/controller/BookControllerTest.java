package com.spring.project01.kaplanjpahibernate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.service.BookService;
import com.spring.project01.kaplanjpahibernate.service.CustomerService;

import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.BDDMockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest()
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenBook_whenBookIsCreated_thenReturnSavedBook
            () throws Exception {
        //given

        Book book = Book.builder().bookName("test").genre("test").build();
        given(bookService.saveBook(ArgumentMatchers.any(Book.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

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

        given(bookService.getAllBooks())
                .willReturn(books);

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
        //given
        long bookId = 1L;
        Book book = Book.builder().bookName("test").genre("test").build();


        given(bookService.getBookByID(bookId))
                .willReturn(Optional.of(book));

        //when
        ResultActions perform = mockMvc.perform(get("/api/book/{id}", bookId));

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
        long bookId = 1L;
        Book book = Book.builder().bookId(1L).bookName("test").genre("test").build();
        System.out.println(book.getBookId());

        given(bookService.getBookByID(bookId))
                .willReturn(Optional.empty());

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

        Book updatedBook = Book.builder().bookName("updated").genre("updated").build();

        given(bookService.getBookByID(bookId))
                .willReturn(Optional.of(book));
        given(bookService.saveBook(ArgumentMatchers.any(Book.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));


        //when
        ResultActions perform = mockMvc.perform(put("/api/book/{id}", bookId)
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
        long bookId = 1L;
        Book book = Book.builder().bookName("test").genre("test").build();

        Book updatedBook = Book.builder().bookName("updated").genre("updated").build();

        given(bookService.getBookByID(bookId))
                .willReturn(Optional.empty());
        given(bookService.saveBook(ArgumentMatchers.any(Book.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));


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
        long bookId = 1L;

        willDoNothing().given(bookService).deleteBookById(bookId);
        //when
        ResultActions perform = mockMvc.perform(delete("/api/book/{id}", bookId));

        perform.andExpect(status().isOk()).andDo(print());

    }


}