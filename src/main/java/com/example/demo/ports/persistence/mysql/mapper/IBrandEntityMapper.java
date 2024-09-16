package com.example.demo.ports.persistence.mysql.mapper;

import com.example.demo.domain.model.Brand;
import com.example.demo.ports.persistence.mysql.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toEntitybrand(Brand brand);
    List<Brand> toBrandList(List<BrandEntity> brandEntityList);
    @Named("idToBrand")
    default BrandEntity idToBrand(Long brandId) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(brandId);
        return brandEntity;
    }
}
