package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RETURN_RECORD")
public class ReturnRecord {
    @Id
    @Column(name = "RETURN_RECORD_ID")
    private int returnRecordId;

    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "RETURN_DATE")
    private Timestamp returnDate;

    @Column(name = "RETURN_REASON")
    private String returnReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "RETURN_STATUS")
    private ReturnStatusEnum returnStatus;
}
