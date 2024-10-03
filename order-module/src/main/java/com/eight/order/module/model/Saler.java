package com.eight.order.module.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALER")
public class Saler {
    @Id
    @Column(name = "SALER_ID")
    private String salerId;

    @Column(name = "SALER_NAME")
    private String salerName;

    @Column(name = "SALER_EMAIL")
    private String salerEmail;

    @Column(name = "SALER_PHONE_NUMBER")
    private String salerPhoneNumber;
}
