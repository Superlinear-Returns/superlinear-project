package com.eight.product.module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_COMMENT")
public class ProductComment {
    @Id
    @Column(name="PRODUCT_COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCommentId;
    @Column(name="PRODUCT_ID")
    private Long productId;
    @Column(name="USER_ID")
    private Long userId;
    @Column(name="COMMENT_RANK")
    private Integer commentRank;
    @Column(name="COMMENT_DETAIL")
    private String commentDetail;
    @Column(name="CREATE_TIME")
    private Date createTime;
    @Column(name="UPDATE_TIME")
    private Date updateTime;
}
