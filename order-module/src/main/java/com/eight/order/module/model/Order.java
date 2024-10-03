package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    @Column(name = "SALER_ID")
    private String salerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatusEnum orderStatus;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

}


