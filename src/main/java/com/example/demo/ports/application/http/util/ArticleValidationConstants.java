package com.example.demo.ports.application.http.util;

public class ArticleValidationConstants {
    public static final int ARTICLE_QUANTITY_MIN_VALUE = 0;
    public static final int ARTICLE_QUANTITY_MAX_VALUE = 1000;
    public static final int ARTICLE_CATEGORY_MIN_SIZE = 1;
    public static final int ARTICLE_CATEGORY_MAX_SIZE = 3;
    public static final int ARTICLE_PRICE_MIN_VALUE = 0;
    public static final String ARTICLE_PRICE_MIN_MESSAGE = "The price must be a positive value";
    public static final String ARTICLE_NAME_REQUIRED_MESSAGE = "Product name is required";
    public static final String ARTICLE_DESCRIPTION_REQUIRED_MESSAGE = "Product description is required";
    public static final String ARTICLE_QUANTITY_REQUIRED_MESSAGE = "Product quantity is required";
    public static final String ARTICLE_QUANTITY_MIN_MESSAGE = "Product quantity must be at least "+ARTICLE_QUANTITY_MIN_VALUE;
    public static final String ARTICLE_QUANTITY_MAX_MESSAGE = "Product quantity must be less than or equal to "+ARTICLE_QUANTITY_MAX_VALUE;
    public static final String ARTICLE_PRICE_REQUIRED_MESSAGE = "Product price is required";
    public static final String ARTICLE_BRAND_REQUIRED_MESSAGE = "Every product needs a brand";
    public static final String ARTICLE_BRAND_ID_POSITIVE_MESSAGE = "Brand ID must be a valid id greater than 0";
    public static final String ARTICLE_CATEGORY_REQUIRED_MESSAGE = "Every product needs at least one category";
    public static final String ARTICLE_CATEGORY_SIZE_MESSAGE = "A product must have between " + ARTICLE_CATEGORY_MIN_SIZE + " and "+ARTICLE_CATEGORY_MAX_SIZE+" categories";
    public static final String ARTICLE_CATEGORY_UNIQUE_MESSAGE = "Every product needs unique categories";

    private ArticleValidationConstants() {
    }
}
