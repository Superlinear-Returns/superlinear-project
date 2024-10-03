package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS_DETAIL")
public class OrderDetail {

    @Id
    @Column(name = "ORDER_DETAIL_ID")
    private String orderDetailId;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
}
