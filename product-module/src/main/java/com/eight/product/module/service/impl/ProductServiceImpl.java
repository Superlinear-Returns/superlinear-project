package com.eight.product.module.service.impl;

import com.eight.product.module.dto.ProductParams;
import com.eight.product.module.repository.ProductRepository;
import com.eight.product.module.service.ProductService;
import com.eight.product.module.model.Product;
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
        System.out.println("11"+product.getPrice());
        product.setCreatedTime(new Date());
        product.setLastModifiedTime(new Date());

        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public Product updateProduct(ProductParams productParams, Long productId) {
        Product oldProduct = productRepository.findById(productId).orElse(null);
        if(oldProduct != null){
            oldProduct.setProductName(productParams.getProductName());
            oldProduct.setPrice(productParams.getPrice());
            oldProduct.setCategory(productParams.getCategory().name());
            oldProduct.setDescription(productParams.getDescription());
            oldProduct.setLastModifiedTime(new Date());
            return productRepository.save(oldProduct);
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
