package com.cyl.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderInfo {
    /**
     * 订单ID
     */
    private Long id;

//    private Long orderNum;
    /**
     * 用户ID
     */
    private Long userId;

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

    private  Integer delFlag;
}