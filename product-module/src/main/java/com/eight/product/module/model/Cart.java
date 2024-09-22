package com.eight.product.module.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    @Column(name="")
    private Long userId;
    @Column(name="")
    private Long productId;
    @Column(name="")
    private Long quantity;
}
