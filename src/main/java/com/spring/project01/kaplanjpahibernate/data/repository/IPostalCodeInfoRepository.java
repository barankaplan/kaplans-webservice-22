package com.spring.project01.kaplanjpahibernate.data.repository;


import com.spring.project01.kaplanjpahibernate.data.entity.PostalCodeInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPostalCodeInfoRepository extends CrudRepository<PostalCodeInfo, Long> {
    @Query("select pi from PostalCodeInfo pi where pi.postalcode = :postalCode")
    PostalCodeInfo findByPostalCode(String postalCode);

    public boolean existsByPostalcode(String postalCode);

}
