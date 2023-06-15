package com.cyl.example.enums;

import java.util.Arrays;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public enum PaymentMethod {
    BALANCE(1, "余额支付"),
    WECHAT(2, "微信支付");
    private int value;

    private String name;

    PaymentMethod(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PaymentMethod valueOf(int value) {
        return Arrays.stream(values()).filter(x -> x.value() == value).findAny()
                .orElseThrow(() -> new IllegalArgumentException("The value is invalid"));
    }

    public int value() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod setName(String name) {
        this.name = name;
        return this;
    }
}
