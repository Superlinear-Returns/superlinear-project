package com.eight.product_module.service.impl;

import com.eight.product_module.model.Product;
import com.eight.product_module.repository.ProductRepository;
import com.eight.product_module.service.ProductService;
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
