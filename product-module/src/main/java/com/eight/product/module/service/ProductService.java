package com.eight.product.module.service;

import com.eight.product.module.dto.ProductParams;
import com.eight.product.module.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long productId);
    Long addProduct(ProductParams productParams);
    Product updateProduct(ProductParams productParams, Long productId);
    void deleteProduct(Long productId);
}
