package com.cyl.example.enums;

import java.util.Arrays;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public enum OrderType {

    GOODS(0, "商品订单"),
    INSURANCE(1, "保险订单");

    private int value;
    private String desc;

    OrderType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static OrderType valueOf(int value) {
        return Arrays.stream(values()).filter(x -> x.value() == value).findAny()
                .orElse(OrderType.GOODS);
    }

    public int value() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }
}
