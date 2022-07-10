package com.spring.project01.kaplanjpahibernate.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    private int price ;

    @OneToMany
    @JoinColumn(name = "price_id")
    List<Book> books= new ArrayList<>();

    public Detail() {
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long detailId) {
        this.priceId = detailId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
