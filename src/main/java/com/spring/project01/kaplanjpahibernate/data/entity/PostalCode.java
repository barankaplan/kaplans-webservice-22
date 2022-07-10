package com.spring.project01.kaplanjpahibernate.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "postal_code")
public class PostalCode {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postal_code_id")
    private Long postalCodeId;

    public Long getPostalCodeId() {
        return postalCodeId;
    }

    public void setPostalCodeId(Long postalCodeId) {
        this.postalCodeId = postalCodeId;
    }

    private int postalCode;

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }




    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnore
    public Customer customer;

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public PostalCodeInfo getPostalCodeInfo() {
        return postalCodeInfos;
    }

    public void setPostalCodeInfo(PostalCodeInfo postalCodeInfo) {
        this.postalCodeInfos = postalCodeInfo;
    }

    public PostalCodeInfo getPostalCodeInfos() {
        return postalCodeInfos;
    }

    public void setPostalCodeInfos(PostalCodeInfo postalCodeInfos) {
        this.postalCodeInfos = postalCodeInfos;
    }

    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "postal_codes_info_id", nullable = true)
    @JsonIgnore
    public PostalCodeInfo postalCodeInfos;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Transient
    public long customerId;





}
