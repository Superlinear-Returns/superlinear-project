package com.eight.product_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Table(name="PRODUCT_IMAGE")
@Entity
@Getter
@Setter
public class Product_Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_IMAGE_ID")
    private Long productImageId;
    @Column(name="PRODUCT_ID")
    private Long productId;
    @Column(name="IMAGE_URL")
    private String imageUrl;
    @Column(name="IMAGE_TYPE")
    private String imageType;
    @Column(name="IMAGE_SORT")
    private Integer imageSort;
    @Column(name="CREATED_TIME")
    private Date createdTime;
    @Column(name="LAST_MODIFIED_DATE")
    private Date lastModifiedDate;
}
