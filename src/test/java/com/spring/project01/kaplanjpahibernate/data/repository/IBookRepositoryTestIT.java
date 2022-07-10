package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.integration.AbstractContainerBasedTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IBookRepositoryTestIT extends AbstractContainerBasedTest {

    @Autowired
    private IBookRepository iBookRepository;

    private Book book;



    @Test
    void findByBookId() {
            Book entity = new Book();
            entity.setBookName("bbb");
            iBookRepository.save(entity);
            Assertions.assertNotNull(iBookRepository.findById(entity.getBookId()));

    }

}