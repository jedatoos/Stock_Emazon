package com.example.demo.ports.application.http.dto.article;

import com.example.demo.ports.application.http.util.ArticleValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for Article")
public class ArticleRequest implements Serializable {
    @Schema(description = "Name of the article", example = "Diadema")
    @NotBlank(message = ArticleValidationConstants.ARTICLE_NAME_REQUIRED_MESSAGE)
    private String articleName;

    @Schema(description = "Description of the article", example = "Diadema con sonido envolvente")
    @NotBlank(message = ArticleValidationConstants.ARTICLE_DESCRIPTION_REQUIRED_MESSAGE)
    private String articleDescription;

    @Schema(description = "Quantity of the article", example = "5")
    @NotNull(message = ArticleValidationConstants.ARTICLE_QUANTITY_REQUIRED_MESSAGE)
    @Min(value = ArticleValidationConstants.ARTICLE_QUANTITY_MIN_VALUE, message = ArticleValidationConstants.ARTICLE_QUANTITY_MIN_MESSAGE)
    @Max(value = ArticleValidationConstants.ARTICLE_QUANTITY_MAX_VALUE, message = ArticleValidationConstants.ARTICLE_QUANTITY_MAX_MESSAGE)
    private Integer articleQuantity;

    @Schema(description = "Price of the article", example = "1000")
    @NotNull(message = ArticleValidationConstants.ARTICLE_PRICE_REQUIRED_MESSAGE)
    @Min(value = ArticleValidationConstants.ARTICLE_PRICE_MIN_VALUE, message = ArticleValidationConstants.ARTICLE_PRICE_MIN_MESSAGE)
    private Double articlePrice;

    @Schema(description = "ID of the brand", example = "11")
    @NotNull(message = ArticleValidationConstants.ARTICLE_BRAND_REQUIRED_MESSAGE)
    @Positive(message = ArticleValidationConstants.ARTICLE_BRAND_ID_POSITIVE_MESSAGE)
    private Long brandId;

    @Schema(description = "List of category IDs", example = "[1, 2, 3]")
    @NotNull(message = ArticleValidationConstants.ARTICLE_CATEGORY_REQUIRED_MESSAGE)
    @Size(min = ArticleValidationConstants.ARTICLE_CATEGORY_MIN_SIZE, max = ArticleValidationConstants.ARTICLE_CATEGORY_MAX_SIZE, message = ArticleValidationConstants.ARTICLE_CATEGORY_SIZE_MESSAGE)
    @UniqueElements(message = ArticleValidationConstants.ARTICLE_CATEGORY_UNIQUE_MESSAGE)
    private List<Long> categoryIds;
}
