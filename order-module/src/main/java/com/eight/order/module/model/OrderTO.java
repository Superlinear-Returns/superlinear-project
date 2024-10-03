package com.eight.order.module.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderTO {

    @NotBlank
    private String orderId;

    private String customerId;

    private LocalDate orderDate;

    private String salerId;

    private OrderStatusEnum orderStatus;

    private BigDecimal totalAmount;

}


