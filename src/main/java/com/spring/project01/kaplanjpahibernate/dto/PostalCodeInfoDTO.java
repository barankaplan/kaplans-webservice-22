package com.spring.project01.kaplanjpahibernate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PostalCodeInfoDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminName2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double lat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double lng;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String postalcode;


    @Override
    public String toString() {
        return "PostalCodeInfoDTO{" +
                "adminName2='" + adminName2 + '\'' +
                '}';
    }
}
