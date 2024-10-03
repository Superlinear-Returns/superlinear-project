package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_EVAL")
public class OrderEval {
    @Id
    @Column(name = "ORDER_EVAL_ID")
    private String orderEvalId;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "EVAL_DATE")
    private LocalDate evalDate;
}

