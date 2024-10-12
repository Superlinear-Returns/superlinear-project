package com.eight.order.module.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum ReturnStatusEnum {

    PENDING(0, "PENDING"),
    APPROVED(1, "APPROVED"),
    REJECTED(2, "REJECTED"),
    COMPLETED(3, "COMPLETED");

    public int code;
    public String codeName;

    ReturnStatusEnum(int i, String pending) {
    }

    public ReturnStatusEnum getReturnStatusByCode(int code){
        return Arrays.stream(ReturnStatusEnum.values())
                .filter(status->status.code == code)
                .findFirst().orElse(null);
    }

    public ReturnStatusEnum getReturnStatusByCodeName(String codeName){
        return Arrays.stream(ReturnStatusEnum.values())
                .filter(status-> StringUtils.endsWith(status.codeName, codeName))
                .findFirst().orElse(null);
    }
}
