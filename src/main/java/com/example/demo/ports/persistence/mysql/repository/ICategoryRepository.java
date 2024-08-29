package com.example.demo.ports.persistence.mysql.repository;


import com.example.demo.ports.persistence.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
