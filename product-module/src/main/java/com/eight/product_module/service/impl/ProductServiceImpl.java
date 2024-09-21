package com.eight.product_module.service.impl;

import com.eight.product_module.dto.ProductParams;
import com.eight.product_module.model.Product;
import com.eight.product_module.repository.ProductRepository;
import com.eight.product_module.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Long addProduct(ProductParams productParams) {
        Product product = convertDtoToEntity(productParams, Product.class);
        product.setCreatedTime(new Date());
        product.setLastModifiedTime(new Date());

        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public Product updateProduct(ProductParams productParams, Long productId) {
        Product oldProduct = productRepository.findById(productId).orElse(null);
        if(oldProduct != null){
            Product updateProduct = convertDtoToEntity(productParams, Product.class);
            updateProduct.setCreatedTime(oldProduct.getCreatedTime());
            updateProduct.setLastModifiedTime(new Date());
            return productRepository.save(updateProduct);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private static <D, T> T convertDtoToEntity (D dto, Class<T> classType){
        return new ModelMapper().map(dto, classType);
    }
}
