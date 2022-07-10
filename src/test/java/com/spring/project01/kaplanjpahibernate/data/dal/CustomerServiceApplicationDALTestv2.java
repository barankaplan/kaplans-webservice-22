package com.spring.project01.kaplanjpahibernate.data.dal;

import com.spring.project01.kaplanjpahibernate.data.entity.BankCard;
import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import com.spring.project01.kaplanjpahibernate.data.repository.*;
import com.spring.project01.kaplanjpahibernate.mapper.IPostalCodeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceApplicationDALTestv2 {

    @Mock
    private ICustomerRepository iCustomerRepository;
    @Mock
    private IBookRepository iBookRepository;

    @InjectMocks
    private CustomerServiceApplicationDAL customerServiceApplicationDAL;

    CustomerServiceApplicationDALTestv2() {
    }

    @BeforeEach
    void setup() {

//        iCustomerRepository = Mockito.mock(ICustomerRepository.class);
//        iBookRepository = Mockito.mock(IBookRepository.class);
//        customerServiceApplicationDAL = new CustomerServiceApplicationDAL(iCustomerRepository, iBookRepository,
//                iCardRepository, iPostalCodeRepository,
//                iPostalCodeInfoRepository, restTemplate,
//                iPostalCodeMapper, movieRepository,
//                iMovieInformationRepository);
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


        given(iBookRepository.findByBookIdTest(1L))
                .willReturn(Optional.of(book));


        when(iCustomerRepository.save(Mockito.any(Customer.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        //when
        Customer newCustomer = customerServiceApplicationDAL.mapCustomerAndBookTested(customer);


        //then
        assertThat(newCustomer.getBooks().stream().anyMatch(b -> Objects.equals(b.getBookName(), "test book"))).isTrue();
        assertThat(newCustomer).isNotNull();


    }

    @Test()
    @DisplayName("JUnit test for a method in dal")
    void givenNullIdCustomerObject_whenSaveCustomer_thenThrowsException
            () {
        Customer customer = Customer.builder().customerId(1L).firstName("baran").build();

        given(iCustomerRepository.findByCustomerId(customer.getCustomerId())).willReturn(Optional.of(customer));

//        when(iCustomerRepository.save(Mockito.any(Customer.class)))
//                .thenAnswer(i -> i.getArguments()[0]);

        Assertions.assertThrows(NullPointerException.class, () -> customerServiceApplicationDAL.mapCustomerAndBookTestedv2(customer));

        verify(iCustomerRepository, never()).save(any(Customer.class));

    }

    @Test()
    @DisplayName("JUnit test for a method in dal")
    void givenIdCustomerObject_whenSaveCustomer_thenThrowsExceptionv2
            () {
        Customer customer = Customer.builder().customerId(null).firstName("baran").build();

        given(iCustomerRepository.findByCustomerId(customer.getCustomerId())).willReturn(Optional.of(customer));

//        when(iCustomerRepository.save(Mockito.any(Customer.class)))
//                .thenAnswer(i -> i.getArguments()[0]);

        Assertions.assertThrows(NullPointerException.class, () -> customerServiceApplicationDAL.mapCustomerAndBookTestedv2(customer));

        verify(iCustomerRepository, never()).save(any(Customer.class));

    }

    @Test()
    @DisplayName("JUnit test getAllCustomer positive")
    void givenEmptyCustomerList_whenGetAllCustomer_thenReturnCustomerList
            () {
        Customer customer = Customer.builder().customerId(1L).firstName("baran").build();
        Customer customer2 = Customer.builder().customerId(2L).firstName("Kaplan").build();

        given(iCustomerRepository.findAll()).willReturn(List.of(customer, customer2));

//        when(iCustomerRepository.save(Mockito.any(Customer.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
        assertThat(iCustomerRepository.findAll()).isNotNull();
        assertThat(iCustomerRepository.findAll().size()).isEqualTo(2);


    }

    @Test()
    @DisplayName("JUnit test getAllCustomer negative")
    void givenEmptyCustomerList_whenGetAllCustomer_thenReturnEmptyCustomerList
            () {
        Customer customer = Customer.builder().customerId(1L).firstName("baran").build();
        Customer customer2 = Customer.builder().customerId(2L).firstName("Kaplan").build();

        given(iCustomerRepository.findAll()).willReturn(Collections.emptyList());

//        when(iCustomerRepository.save(Mockito.any(Customer.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
        assertThat(iCustomerRepository.findAll()).isEmpty();
        assertThat(iCustomerRepository.findAll().size()).isEqualTo(0);

    }

    @Test
    @DisplayName("junit  for get customer method")
    void givenCustomerId_whenGetCustomerById_thenReturnCustomerObject
            () {
        //given
        Customer customer = Customer.builder().customerId(1L).firstName("baran").build();

        given(iCustomerRepository.findByCustomerId(1L)).willReturn(Optional.of(customer));
        //when

        //then
        assertThat(iCustomerRepository.findByCustomerId(customer.getCustomerId())).isNotNull();

    }


    @Test
    @DisplayName("junit  for void update method")
    void givenCustomerId_whenUpdateCustomerName_thenReturnUpdatedCustomerObject
            () {
        Customer customer = Customer.builder().customerId(2L).firstName("baran").build();

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            String name = invocation.getArgument(1);
            System.out.println("called for id: " + id + " and name: " + name);

            assertEquals(1L, id);
            assertEquals("kaplan test", name);

            return null;
        }).when(iCustomerRepository).updateCustomer(anyLong(), anyString());

        iCustomerRepository.updateCustomer(1L, "kaplan test");
        verify(iCustomerRepository, times(1)).updateCustomer(1L, "kaplan test");


    }

    @Test
    @DisplayName("junit  for void delete method")
    void givenCustomerId_whenDeleteCustomer_thenDeleteCustomerObject
            () {
        Customer customer = Customer.builder().customerId(1L).firstName("baran").build();

        willDoNothing().given(iCustomerRepository).deleteCustomerByCustomerId(1L);


        iCustomerRepository.deleteCustomerByCustomerId(customer.getCustomerId());


        verify(iCustomerRepository,times(1)).deleteCustomerByCustomerId(1L);
    }


}