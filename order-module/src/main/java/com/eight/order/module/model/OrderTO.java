package com.eight.order.module.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class OrderTO {

    @NotBlank
    private int orderId;

    private int customerId;

    private Timestamp orderDate;

    private int salerId;

    private OrderStatusEnum orderStatus;

    private BigDecimal totalAmount;

}


