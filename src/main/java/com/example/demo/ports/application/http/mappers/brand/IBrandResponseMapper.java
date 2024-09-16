package com.example.demo.ports.application.http.mappers.brand;

import com.example.demo.domain.model.Brand;
import com.example.demo.ports.application.http.dto.brand.BrandResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse brandToBrandResponse(Brand brand);
    List<BrandResponse> brandsToBrandResponses(List<Brand> brands);
}
