package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import com.spring.project01.kaplanjpahibernate.data.entity.PostalCode;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostalCodeRepository extends JpaRepository<PostalCode,Long>
{
    public boolean existsPostalCodeByPostalCode (Integer postalCode);
    @Query("select pi from PostalCode pi where pi.customer.customerId = :customerId ")
    public Optional<PostalCode> findByCustomerId(long customerId);

}