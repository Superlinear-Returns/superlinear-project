package com.eight.order.module.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum OrderStatusEnum {

    PENDING(0, "PENDING"),
    PROCESSING(1, "PROCESSING"),
    SHIPPED(2, "SHIPPED"),
    DELIVERED(3, "DELIVERED"),
    CANCELLED(4, "CANCELLED");

    public int code;
    public String codeName;

    OrderStatusEnum(int i, String pending) {
    }

    public static OrderStatusEnum getOrderStatusByCode(int code){
        return Arrays.stream(OrderStatusEnum.values())
                .filter(status->status.code == code)
                .findFirst().orElse(null);
    }

    public static OrderStatusEnum getOrderStatusByCodeName(String codeName){
        return Arrays.stream(OrderStatusEnum.values())
                .filter(status-> StringUtils.endsWith(status.codeName, codeName))
                .findFirst().orElse(null);
    }
}
