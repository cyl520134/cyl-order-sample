package com.cyl.example.enums;

import java.util.Arrays;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public enum OrderStatus {

    UNPAID(0, "待支付"),
    PAID(1, "已支付"),
    CANCELED(2, "已取消"),
    AFTER_SALE(3, "售后中"),
    REFUNDED(4, "已退款"),
    CLOSED(5, "已关闭");

    private int value;

    private String desc;

    OrderStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static OrderStatus valueOf(int value) {
        return Arrays.stream(values()).filter(x -> x.value() == value).findAny()
                .orElseThrow(() -> new IllegalArgumentException("The value is invalid"));
    }

    public int value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }
}
