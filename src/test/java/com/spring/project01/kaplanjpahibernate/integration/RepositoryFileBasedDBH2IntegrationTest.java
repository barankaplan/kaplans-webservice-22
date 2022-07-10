package com.spring.project01.kaplanjpahibernate.integration;

import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.repository.IBookRepository;

import com.spring.project01.kaplanjpahibernate.configuration.H2JpaConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {H2JpaConfig.class })
@ActiveProfiles("H2JpaConfig")
@Sql({"/test.sql"})
 class RepositoryFileBasedDBH2IntegrationTest {



    @Autowired
    private IBookRepository iBookRepository;




    @Test
     void test() {
        Book entity = new Book();
        entity.setBookName("bbb");
        iBookRepository.save(entity);
        Assertions.assertNotNull(iBookRepository.findBookByBookName("bbb"));
    }

    @Test
     void test2() {
//        Book entity = new Book();
//        entity.setBookId(1L);
//        entity.setBookName("updated book v6");
//        entity.setGenre("updated genre");
//        iBookRepository.save(entity);
        Assertions.assertNotNull(iBookRepository.findBookByBookName("Life of Pi"));
    }




}