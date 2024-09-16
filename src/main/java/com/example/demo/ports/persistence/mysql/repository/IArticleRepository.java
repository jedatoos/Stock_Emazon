package com.example.demo.ports.persistence.mysql.repository;

import com.example.demo.ports.persistence.mysql.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
