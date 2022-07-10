package com.spring.project01.kaplanjpahibernate.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {


    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "first_name")
    private String firstName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @ManyToMany(mappedBy = "customers")
    private Set<Book> books = new HashSet<>();

    public Set<Book> getBooks() {
        return books;
    }




    //    @ManyToMany(mappedBy = "customers")
//    private Set<MovieEntity> movieEntities = new HashSet<>();


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<BankCard> bankCards = new ArrayList<>();

    public List<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCard> bankCards) {
        this.bankCards = bankCards;
    }


    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private PostalCode pcodes;

    @ManyToMany(mappedBy = "customers")
    private Set<MovieInformation> movieInformations = new LinkedHashSet<>();

    public Set<MovieInformation> getMovieInformations() {
        return movieInformations;
    }

    public void setMovieInformations(Set<MovieInformation> movieInformations) {
        this.movieInformations = movieInformations;
    }


    public PostalCode getPcodes() {
        return pcodes;
    }

    public void setPcodes(PostalCode pcodes) {
        this.pcodes = pcodes;
    }


    public void removeBook(Book book) {
        this.books.remove(book);
        book.getCustomers().remove(this);
    }


//    @Transient
//    public List<Integer> movieId;
//
//    public List<Integer> getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(List<Integer> movieId) {
//        this.movieId = movieId;
//    }


}
