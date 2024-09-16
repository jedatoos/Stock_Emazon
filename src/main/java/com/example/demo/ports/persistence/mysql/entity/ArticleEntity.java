package com.example.demo.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")  // Mapeo explícito de la columna 'id'
    private Long articleId;

    @Column(name = "article_name", nullable = false, length = 60)  // Maps to 'name' column in the DB
    private String articleName;

    @Column(name = "article_description", nullable = false, length = 90)  // Mapping for 'description' column
    private String articleDescription;

    @Column(name = "article_quantity", nullable = false)
    private int articleQuantity;

    @Column(name = "article_price", nullable = false)
    private BigDecimal articlePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();



}
