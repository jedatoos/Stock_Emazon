package com.example.demo.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name", nullable = false, unique = true, length = 50)
    private String categoryName;

    @Column(name = "category_description", nullable = false, length = 90)
    private String categoryDescription;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private Set<ArticleEntity> article;


}
