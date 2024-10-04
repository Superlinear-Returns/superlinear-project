package com.eight.product.module.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "CART")
public class Cart {
    @Column(name="")
    private Long userId;
    @Column(name="")
    private Long productId;
    @Column(name="")
    private Long quantity;
}
