package com.spring.project01.kaplanjpahibernate.data.repository;


import com.spring.project01.kaplanjpahibernate.data.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICardRepository extends JpaRepository<BankCard, Long> {



}
