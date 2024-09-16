package com.example.demo.domain.model;

import java.util.List;

public class Article {
    private Long articleId;
    private String articleName;
    private String articleDescription;
    private Integer articleQuantity;
    private Double articlePrice;
    private Long brandId;
    private List<Long> categoryIds;

    // Constructores
    public Article() {
    }

    public Article(Long articleId, String articleName, String articleDescription, Integer articleQuantity, Double articlePrice, Long brandId, List<Long> categoryIds) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleDescription = articleDescription;
        this.articleQuantity = articleQuantity;
        this.articlePrice = articlePrice;
        this.brandId = brandId;
        this.categoryIds = categoryIds;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public Integer getArticleQuantity() {
        return articleQuantity;
    }

    public void setArticleQuantity(Integer articleQuantity) {
        this.articleQuantity = articleQuantity;
    }

    public Double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
