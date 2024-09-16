package com.example.demo.ports.application.http.mappers.brand;

import com.example.demo.domain.model.Brand;
import com.example.demo.ports.application.http.dto.brand.BrandRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    Brand brandRequestToBrand(BrandRequest brandRequest);
}
