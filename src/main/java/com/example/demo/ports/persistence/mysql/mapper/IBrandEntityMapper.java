package com.example.demo.ports.persistence.mysql.mapper;

import com.example.demo.domain.model.Brand;
import com.example.demo.ports.persistence.mysql.entity.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toEntitybrand(Brand brand);
    List<Brand> toBrandList(List<BrandEntity> brandEntityList);
}
