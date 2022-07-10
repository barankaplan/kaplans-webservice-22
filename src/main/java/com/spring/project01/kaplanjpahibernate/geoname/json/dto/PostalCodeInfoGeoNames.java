package com.spring.project01.kaplanjpahibernate.geoname.json.dto;

public class PostalCodeInfoGeoNames {
    public String adminName2;
    public double lat;
    public double lng;
    public String postalcode;


    @Override
    public String toString() {
        return "PostalCodeInfoGeoNames{" +
                "adminName2='" + adminName2 + '\'' +
                '}';
    }
}
