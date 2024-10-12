package com.eight.order.module.model;

import lombok.Data;

@Data
public class DataObject {

    private final String data;

    private static int dataCounter = 0;

    public static DataObject get(String data) {
        dataCounter++;
        return new DataObject(data);
    }
}
