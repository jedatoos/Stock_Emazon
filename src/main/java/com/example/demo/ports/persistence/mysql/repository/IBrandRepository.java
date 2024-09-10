package com.example.demo.ports.persistence.mysql.repository;

import com.example.demo.ports.persistence.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByBrandName(String brandName);

}
