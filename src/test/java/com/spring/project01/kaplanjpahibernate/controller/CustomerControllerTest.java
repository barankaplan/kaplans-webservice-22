package com.spring.project01.kaplanjpahibernate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.service.BookService;
import com.spring.project01.kaplanjpahibernate.service.CustomerService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class CustomerControllerTest {
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
        BDDMockito.given(bookService.saveBook(ArgumentMatchers.any(Book.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        //when
        ResultActions perform = mockMvc.perform(post("/api/customer/save/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        //then

        perform.andExpect(MockMvcResultMatchers.status().isCreated()).
                andExpect(MockMvcResultMatchers.jsonPath("$.bookId", CoreMatchers.is(book.getBookId()))).
                andExpect(MockMvcResultMatchers.jsonPath("$.bookName", CoreMatchers.is(book.getBookName()))).
                andExpect(MockMvcResultMatchers.jsonPath("$.genre", CoreMatchers.is(book.getGenre() )));

    }

}