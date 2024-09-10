package com.example.demo.ports.application.http.util;

public class BrandValidationConstants {
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final String NAME_REQUIRED_MESSAGE = "Brand name is required";
    public static final String DESCRIPTION_REQUIRED_MESSAGE = "Brand  description is required";
    public static final int MIN_LENGTH_BRAND=3;
    public static final String NAME_LENGTH_MESSAGE = "The Brand  name must be between " + MIN_LENGTH_BRAND + " and 50 characters";
    public static final String DESCRIPTION_LENGTH_MESSAGE = "The Brand  description must be between " + MIN_LENGTH_BRAND + " and 90 characters";

    private BrandValidationConstants() {
    }

}
