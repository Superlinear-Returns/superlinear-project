package com.eight.product.module.controller;

import com.eight.product.module.dto.ProductTo;
import com.eight.product.module.model.Product;
import com.eight.product.module.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "getProductById", description = "使用productId獲取商品")
    public ResponseEntity<Product> getProductById(@RequestParam("productId") Long productId) {
        Product defaultProduct = new Product();
        return ResponseEntity.ok(productService
                .getProductById(productId).orElse(defaultProduct));
    }

    @PostMapping("/add_product")
    @Operation(summary = "addProduct", description = "新增商品")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductTo productTo) {
        Product defaultProduct = new Product();
        Long productId = productService.addProduct(productTo);
        Product product = productService.getProductById(productId).orElse(defaultProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/update_product/{productId}")
    @Operation(summary = "updateProduct", description = "更新商品")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductTo productTo,
                                                 @PathVariable Long productId) {
        Product product = productService.updateProduct(productTo, productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/delete_product/{productId}")
    @Operation(summary = "updateProduct", description = "刪除商品")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }


}
