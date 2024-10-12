package com.eight.user.module.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BLACKLIST")
public class Blacklist extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "REMARK")
    private String remark;

    public Blacklist() {
    }

    public Blacklist(String ipAddress, String remark) {
        this.ipAddress = ipAddress;
        this.remark = remark;
    }
}
