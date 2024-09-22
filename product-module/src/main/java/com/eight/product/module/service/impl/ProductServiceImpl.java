package com.eight.product.module.service.impl;

import com.eight.product.module.repository.ProductRepository;
import com.eight.product.module.service.ProductService;
import com.eight.product.module.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}
