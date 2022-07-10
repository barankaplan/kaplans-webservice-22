package com.spring.project01.kaplanjpahibernate.geoname.json.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PostalCodeGeoNames {
    @JsonProperty("postalcodes")
    public final List<PostalCodeInfoGeoNames> postalCodeGeoNames = new ArrayList<>();
    public Status status;
}
