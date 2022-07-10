package com.spring.project01.kaplanjpahibernate.mapper;



import com.spring.project01.kaplanjpahibernate.data.entity.PostalCodeInfo;
import com.spring.project01.kaplanjpahibernate.dto.PostalCodeInfoDTO;
import com.spring.project01.kaplanjpahibernate.geoname.json.dto.PostalCodeInfoGeoNames;
import org.mapstruct.Mapper;

@Mapper(implementationName = "PostalCodeInfoMapper", componentModel = "spring")
public interface IPostalCodeMapper {
    PostalCodeInfoDTO toPostalCodeInfoDTO(PostalCodeInfo postalCode);
    PostalCodeInfo toPostalCodeInfo(PostalCodeInfoDTO postalCodeInfoDTO);
    PostalCodeInfoDTO toPostalCodeInfoDTO(PostalCodeInfoGeoNames postalCodeInfoGeoNames);
    PostalCodeInfo toPostalCodeInfo(PostalCodeInfoGeoNames postalCodeInfoGeoNames);
}
