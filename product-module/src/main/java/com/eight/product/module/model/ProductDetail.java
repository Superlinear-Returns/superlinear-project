package com.eight.product.module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetail {
    @Id
    @Column(name="PRODUCT_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;
    @Column(name="PRODUCT_ID")
    private Long productId;
    @Column(name="SPECIFICATION")
    private String specification;
    @Column(name="ADDITIONAL_CATEGORIES")
    private String additionalCategories;
    @Column(name="CREATE_TIME")
    private Date createTime;
    @Column(name="UPDATE_TIME")
    private Date updateTime;
}