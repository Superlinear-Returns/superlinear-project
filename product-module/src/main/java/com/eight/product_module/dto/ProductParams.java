package com.eight.product_module.dto;

import com.eight.product_module.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParams {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private Integer price;
    @NotNull
    private String description;
}
