package com.eight.product_module.controller;

import com.eight.product_module.model.Product;
import com.eight.product_module.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Product> getProductById(@RequestParam("productId") Long productId) {
        System.out.println("111");
        Product defaultProduct = new Product();
        return ResponseEntity.ok(productService
                .getProductById(productId).orElse(defaultProduct));
    }

}
