package com.cyl.example.service;


import com.cyl.example.dto.OrderDelResDto;
import com.cyl.example.dto.OrderInfoDto;
import com.cyl.example.dto.OrderInfoResDto;

import java.util.List;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public interface OrderService {
    /**
     * 新增订单
     * @param orderInfoDto
     * @return
     */
    void addOrder(OrderInfoDto orderInfoDto);

    /**
     * 更新订单-主要涉及更新订单状态
     * @param orderInfoDto
     * @return
     */
    Integer modifyOrder(OrderInfoDto orderInfoDto);

    /**
     * 根据用户ID和订单ID查询订单数据，限制用户只能查看自己创建的订单
     * @param accountId
     * @param id
     * @return
     */
    OrderInfoResDto getOrderById(Long accountId, Long id);

    /**
     * 删除订单----限制用户只能删除自己的订单
     * @param accountId
     * @param id
     * @return
     */
    void delOrderById(Long accountId, Long id);

    /**
     * 批量删除
     * @param userId
     * @param ids
     * @return
     */
    OrderDelResDto delOrderByIds(Long userId, List<Long> ids);
}
