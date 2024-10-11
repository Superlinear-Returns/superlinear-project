package com.eight.product.module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CART_ID")
    private Long cartId;
    @Column(name="USER_ID")
    private Long userId;
    @Column(name="PRODUCT_ID")
    private Long productId;
    @Column(name="QUANTITY")
    private Long quantity;
}
