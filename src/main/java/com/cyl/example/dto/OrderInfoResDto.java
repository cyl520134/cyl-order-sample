package com.cyl.example.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@Data
public class OrderInfoResDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**
     * 支付方式
     */
    private Integer paymentMethod;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;
    /**
     * 优惠后金额
     */
    private BigDecimal realMoney;
    /**
     * 优惠金额
     */
    private BigDecimal discountMoney;

    /**
     * 支付金额
     */
    private BigDecimal payMoney;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 订单类型
     */
    private Integer type;
    /**
     * 支付状态
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 支付时间
     */
    private Date paidTime;

    /**
     * 成功时间
     */
    private Date successTime;
}
