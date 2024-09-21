package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "ORDER_DATE")
    private Date OrderDate;

    @Column(name = "ORDER_DATA")
    private String OrderData;

}
