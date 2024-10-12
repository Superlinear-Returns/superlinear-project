package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SPL_ORDER")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @Column(name = "SALER_ID")
    private int salerId;

    @Column(name = "ORDER_STATUS")
    private int orderStatus;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

}


