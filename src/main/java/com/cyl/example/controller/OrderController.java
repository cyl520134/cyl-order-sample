package com.cyl.example.controller;

import com.cyl.example.dto.OrderDelResDto;
import com.cyl.example.dto.OrderInfoDto;
import com.cyl.example.dto.OrderInfoResDto;
import com.cyl.example.dto.Result;
import com.cyl.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 新增订单
     *
     * @param userId
     * @param orderInfoDto
     */
    @PostMapping("/add/{userId}")
    @ResponseBody
    public Result<Integer> addOrder(@PathVariable Long userId, @RequestBody OrderInfoDto orderInfoDto) {
        orderInfoDto.setUserId(userId);
        orderService.addOrder(orderInfoDto);
        return Result.ok();
    }

    /**
     * 修改订单
     *
     * @param userId
     * @param orderInfoDto
     */
    @PostMapping("/modify/{userId}")
    @ResponseBody
    public Result<Integer> modifyOrder(@PathVariable Long userId, @RequestBody OrderInfoDto orderInfoDto) {
        orderInfoDto.setUserId(userId);
        return Result.ok(orderService.modifyOrder(orderInfoDto));
    }

    /**
     * 用户指定订单ID查询
     *
     * @param userId
     * @param id
     */
    @GetMapping("/get/{userId}/{id}")
    public Result<OrderInfoResDto> getOrderByID(@PathVariable Long userId, @PathVariable Long id) {
        OrderInfoResDto orderInfo = orderService.getOrderById(userId, id);
        return Result.ok(orderInfo);
    }

    /**
     * 用户指定订单ID删除
     *
     * @param userId
     * @param id
     */
    @DeleteMapping("/del/{userId}/{id}")
    public Result<Integer> delOrderByID(@PathVariable Long userId, @PathVariable Long id) {
        orderService.delOrderById(userId, id);
        return Result.ok();
    }

    /**
     * 用户指定订单Id列表删除
     *
     * @param userId
     * @param ids
     */
    @DeleteMapping("/batchDel/{userId}")
    @ResponseBody
    public Result<OrderDelResDto> batchDel(@PathVariable Long userId, @RequestBody List<Long> ids) {
        return Result.ok(orderService.delOrderByIds(userId, ids));
    }

}
