package com.eight.product.module.service;

import com.eight.product.module.dto.ProductTo;
import com.eight.product.module.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long productId);
    Long addProduct(ProductTo productTo);
    Product updateProduct(ProductTo productTo, Long productId);
    void deleteProduct(Long productId);
}
