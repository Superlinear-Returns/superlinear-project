package com.eight.product_module.controller;

import com.eight.product_module.dto.ProductParams;
import com.eight.product_module.model.Product;
import com.eight.product_module.service.ProductService;
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
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductParams productParams) {
        Product defaultProduct = new Product();
        Long productId = productService.addProduct(productParams);
        Product product = productService.getProductById(productId).orElse(defaultProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/update_product/{productId}")
    @Operation(summary = "updateProduct", description = "更新商品")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductParams productParams,
                                                 @PathVariable Long productId) {
        Product product = productService.updateProduct(productParams, productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/delete_product/{productId}")
    @Operation(summary = "updateProduct", description = "刪除商品")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }


}
