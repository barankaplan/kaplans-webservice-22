package com.spring.project01.kaplanjpahibernate.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bank_card")
public class BankCard {

    public BankCard() {

    }

    @Id
    @Column(name = "card_id")
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    private boolean contactlessPayment;

    public boolean isContactlessPayment() {
        return contactlessPayment;
    }

    public void setContactlessPayment(boolean contactlessPayment) {
        this.contactlessPayment = contactlessPayment;
    }





    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    public Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
