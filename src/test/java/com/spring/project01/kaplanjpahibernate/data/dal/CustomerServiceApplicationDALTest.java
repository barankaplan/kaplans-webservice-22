package com.spring.project01.kaplanjpahibernate.data.dal;

import com.spring.project01.kaplanjpahibernate.data.entity.BankCard;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import com.spring.project01.kaplanjpahibernate.data.repository.*;
import com.spring.project01.kaplanjpahibernate.mapper.IPostalCodeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.util.*;

class CustomerServiceApplicationDALTest {

    private ICustomerRepository iCustomerRepository;
    private IBookRepository iBookRepository;
    private ICardRepository iCardRepository;
    private IPostalCodeRepository iPostalCodeRepository;
    private IPostalCodeInfoRepository iPostalCodeInfoRepository;
    private RestTemplate restTemplate;
    private IPostalCodeMapper iPostalCodeMapper;
    private IMovieRepository movieRepository;
    private IMovieInformationRepository iMovieInformationRepository;
    private CustomerServiceApplicationDAL customerServiceApplicationDAL;

    CustomerServiceApplicationDALTest() {
    }

    @BeforeEach
    void setup() {
        iCustomerRepository = Mockito.mock(ICustomerRepository.class);
        iBookRepository = Mockito.mock(IBookRepository.class);
        customerServiceApplicationDAL = new CustomerServiceApplicationDAL(iCustomerRepository, iBookRepository,
                iCardRepository, iPostalCodeRepository,
                iPostalCodeInfoRepository, restTemplate,
                iPostalCodeMapper, movieRepository,
                iMovieInformationRepository);
    }


    @Test
    @DisplayName("JUnit test for a method in dal")
    void givenCustomerObject_whenSaveCustomer_thenReturnCustomerObject
            () {
        //given


        ArrayList<BankCard> cards = new ArrayList<>();
        BankCard card1 = new BankCard();
        card1.setCardId(12345L);
        card1.setContactlessPayment(true);
        cards.add(card1);
        Customer customer = Customer.builder().customerId(1L).firstName("baran").build();
        Book book = new Book();
        book.setBookId(1L);
        book.setBookName("test book");
        Set<Book> books = new HashSet<>();
        books.add(book);
        book.getCustomers().add(customer);
        customer.setBooks(books);


        BDDMockito.given(iBookRepository.findByBookIdTest(1L))
                .willReturn(Optional.of(book));

        when(iCustomerRepository.save(Mockito.any(Customer.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        //when
        Customer newCustomer = customerServiceApplicationDAL.mapCustomerAndBookTested(customer);



        //then
        assertThat(newCustomer.getBooks().stream().anyMatch(b -> Objects.equals(b.getBookName(), "test book"))).isTrue();
        assertThat(newCustomer).isNotNull();


    }



}