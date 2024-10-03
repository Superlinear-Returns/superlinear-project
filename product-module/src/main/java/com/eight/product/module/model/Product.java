package com.eight.product.module.model;

import com.eight.product.module.constant.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name="PRODUCT_NAME")
    private String productName;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="CATEGORY")
    private String  category;
    @Column(name="CREATED_TIME")
    private Date createdTime;
    @Column(name="LAST_MODIFIED_TIME")
    private Date lastModifiedTime;
}
