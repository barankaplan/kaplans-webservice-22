package com.spring.project01.kaplanjpahibernate.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "postal_codes_info")
public class PostalCodeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long postalCodeInfoId;



    @Column(name = "city")
    public String adminName2;

    public String postalcode;



    public double lat;
    
    public double lng;


    public Set<PostalCode> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(PostalCode postalCode) {
        postalCodes.add(postalCode);
    }


    @OneToMany(mappedBy = "postalCodeInfos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<PostalCode> postalCodes
            =new HashSet<>()
            ;


}
