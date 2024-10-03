package com.eight.product.module.dto;

import com.eight.product.module.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductParams {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
}
