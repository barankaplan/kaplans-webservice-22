package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.BankCard;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@DataJpaTest(properties = "spring.main.lazy-initialization=true")
class ICustomerRepositoryTest {
    @Autowired
    private ICustomerRepository iCustomerRepository;
    private BankCard card1 =new BankCard();
    private List<BankCard> cards= new ArrayList<>();
    private BankCard card2 = new BankCard();
    private Customer customer;

    @BeforeEach
    public void setuo() {
        card1.setCardId(12345L);
        card1.setContactlessPayment(true);
        cards.add(card1);
        card2.setCardId(12346L);
        card2.setContactlessPayment(true);
        cards.add(card2);

        customer = Customer.builder().firstName("baran").bankCards(cards)
                .build();

    }

    //    Junit test for save customer
    @Test
    @DisplayName("j unit test for save customer with bank card")
    void givenCustomerObject_whenSave_thenReturnSavedCustomer() {
        //given
        ArrayList<BankCard> cards = new ArrayList<>();
        BankCard card1 = new BankCard();

        card1.setCardId(12345L);
        card1.setContactlessPayment(true);
        cards.add(card1);

        Customer customer = Customer.builder().firstName("baran").bankCards(cards)
                .build();

        //when
        Customer savedCustomer = iCustomerRepository.save(customer);

        //then -verify the output
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getBankCards().get(0).getCardId()).isEqualTo(12345L);

    }


    @Test
    @DisplayName("JUnit test for get all customer and some operations on them")
    void givenCustomersList_whenFindAll_thenEmployeesList() {
        //given
        Customer customer1 = Customer.builder().firstName("baran").build();
        Customer customer2 = Customer.builder().firstName("noahh").build();
        Customer customer3 = Customer.builder().firstName("sarah").build();


        //when
        iCustomerRepository.save(customer1);
        iCustomerRepository.save(customer2);
        iCustomerRepository.save(customer3);
        for (var val :
                iCustomerRepository.findAll()) {
            System.out.println(val.getFirstName());
        }

        //then

        assertThat(iCustomerRepository.findAll().stream().allMatch(str -> str.getFirstName().length() == 5)).isTrue();
        assertThat(iCustomerRepository.findAll().stream().allMatch(str -> str.getFirstName().contains("a"))).isTrue();

    }

    @Test
    @DisplayName("Checking any match method")
    void givenCustomer_whenSave_thenReturnAnyMatch
            () {
        //given
//        ArrayList<BankCard> cards = new ArrayList<>();
//        BankCard card1 = new BankCard();
//        BankCard card2 = new BankCard();
//
//        card1.setCardId(12345L);
//        card1.setContactlessPayment(true);
//        cards.add(card1);
//        card2.setCardId(12346L);
//        card2.setContactlessPayment(true);
//        cards.add(card2);
//
//        Customer customer = Customer.builder().firstName("baran").bankCards(cards)
//                .build();

        //when

        Customer savedCustomer = iCustomerRepository.save(customer);
        //then -verify the output
        assertThat(savedCustomer.getBankCards().stream().anyMatch(card -> card.getCardId() == 12346L)).isTrue();

    }

    @Test
    void givenCustomer_whenUpdate_thenReturnUpdatedCustomer
            () {
        //given
//        ArrayList<BankCard> cards = new ArrayList<>();
//        BankCard card1 = new BankCard();
//        BankCard card2 = new BankCard();
//
//        card1.setCardId(12345L);
//        card1.setContactlessPayment(true);
//        cards.add(card1);
//        card2.setCardId(12346L);
//        card2.setContactlessPayment(true);
//        cards.add(card2);
//
//        Customer customer = Customer.builder().firstName("baran").bankCards(cards)
//                .build();
        iCustomerRepository.save(customer);

        //then
        Customer newCustomer = iCustomerRepository.findByFirstName("baran").get();
        newCustomer.setFirstName("kaplan");
        //when
        assertThat(newCustomer.getFirstName()).isEqualTo("kaplan");
    }

    @Test
    void givenCustomer_whenDelete_thenReturnUpdatedCustomer
            () {
        //given
//        ArrayList<BankCard> cards = new ArrayList<>();
//        BankCard card1 = new BankCard();
//        BankCard card2 = new BankCard();
//
//        card1.setCardId(12345L);
//        card1.setContactlessPayment(true);
//        cards.add(card1);
//        card2.setCardId(12346L);
//        card2.setContactlessPayment(true);
//        cards.add(card2);
//
//        Customer customer = Customer.builder().firstName("baran").bankCards(cards)
//                .build();
        iCustomerRepository.save(customer);

        //then
        iCustomerRepository.deleteById(customer.getCustomerId());
        Optional<Customer> newCustomer = iCustomerRepository.findByFirstName("baran");

        //when
        assertThat(newCustomer).isEmpty();
    }


}