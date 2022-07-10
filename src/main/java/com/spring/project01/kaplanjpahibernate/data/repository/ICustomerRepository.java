package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByCustomerId (Long customerId);

    @Query("select c from Customer c where c.customerId = ?1")
    Optional<Customer> getCustomerByCustomerId (Long id);

    @Transactional
    @Modifying
    @Query("delete from Customer c where c.customerId = ?1")
    void deleteCustomerByCustomerId (Long customerId);

    Optional<Customer> findByFirstName(String name);

    @Modifying
    @Query("update Customer c set c.firstName=?2  where c.customerId = ?1")
    void updateCustomer (Long id,String firstname);

    @Transactional
    @Modifying
    @Query(
            value = "delete\n" +
                    "from customer_book\n" +
                    "where customer_id = :id" +
                    "  and book_id not in (:value)",
            nativeQuery = true)
    public void updateJoinTableCustomerBook(Long id, List<Long> value);

    @Transactional
    @Modifying
    @Query(
            value = "delete\n" +
                    "from customer_movie\n" +
                    "where customer_id = :id" +
                    "  and info_id not in (:value)",
            nativeQuery = true)
    public void updateJoinTableCustomerMovie(Long id, List<Long> value);

    @Transactional
    @Modifying
    @Query(
            value = "delete\n" +
                    "from bank_card\n" +
                    "where customer_id = :id" +
                    "  and card_id not in (:value)",
            nativeQuery = true)
    public void updateBankCard(Long id, List<Long> value);

}
