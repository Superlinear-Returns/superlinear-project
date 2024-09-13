package com.eight.product_module.service;

import com.eight.product_module.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long productId);
}
