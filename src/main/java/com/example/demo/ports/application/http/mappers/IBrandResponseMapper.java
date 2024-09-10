package com.example.demo.ports.application.http.mappers;

import com.example.demo.domain.model.Brand;
import com.example.demo.ports.application.http.dto.BrandResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse brandToBrandResponse(Brand brand);
    List<BrandResponse> brandsToBrandResponses(List<Brand> brands);
}
