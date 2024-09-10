package com.example.demo.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Brand")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @Column(name = "Brand_name", nullable = false, unique = true, length = 50)
    private String brandName;

    @Column(name = "Brand_description", nullable = false, length = 90)
    private String brandDescription;
}
