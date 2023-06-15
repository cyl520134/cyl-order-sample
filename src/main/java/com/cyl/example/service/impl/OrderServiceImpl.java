package com.cyl.example.service.impl;

import com.cyl.example.base.LockUtil;
import com.cyl.example.dao.OrderInfoMapper;
import com.cyl.example.dto.OrderDelResDto;
import com.cyl.example.dto.OrderInfoDto;
import com.cyl.example.dto.OrderInfoResDto;
import com.cyl.example.entity.OrderInfo;
import com.cyl.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderServiceImpl(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    @Override
    public void addOrder(OrderInfoDto orderInfoDto) {
        Date date = new Date();
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoDto, orderInfo);
        orderInfo.setCreateTime(date);
        int i = orderInfoMapper.insertSelective(orderInfo);
        Assert.isTrue(i != 0, "未新增成功");
    }

    @Override
    public Integer modifyOrder(OrderInfoDto orderInfoDto) {
        OrderInfo info = orderInfoMapper.selectByUserIdAndId(orderInfoDto.getUserId(), orderInfoDto.getId());
        Assert.notNull(info, "订单不存在");

        // LockUtil.doInLock封装了锁过程，包括尝试加锁和最终finally中的释放锁
       return LockUtil.doInLock(() -> {
            OrderInfo orderInfo = new OrderInfo();
            BeanUtils.copyProperties(orderInfoDto, orderInfo);
            return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        }, orderInfoDto.getId(), orderInfoDto.getUserId());
    }

    @Override
    public OrderInfoResDto getOrderById(Long accountId, Long id) {
        OrderInfo orderInfo = orderInfoMapper.selectByUserIdAndId(accountId, id);
        //实体转换
        if (orderInfo != null) {
            OrderInfoResDto resDto = new OrderInfoResDto();
            BeanUtils.copyProperties(orderInfo, resDto);
            return resDto;
        }
        return null;
    }

    @Override
    public void delOrderById(Long userId, Long id) {
        OrderInfo orderInfo = orderInfoMapper.selectByUserIdAndId(userId, id);
        Assert.notNull(orderInfo, "订单不存在");

        LockUtil.doInLock(() -> {
            int result = orderInfoMapper.delByUserIdAndId(userId, id);
            Assert.isTrue(result != 0, "未删除成功");
            return null;
        }, id, userId);
    }

    @Override
    public OrderDelResDto delOrderByIds(Long userId, List<Long> ids) {

        int successCount = 0;
        int failureCount = 0;
        int lockedCount = 0;

        for (Long id : ids) {
            try {
                if (LockUtil.tryLock(id, userId)) {
                    //加锁成功执行删除
                    int result = orderInfoMapper.delByUserIdAndId(userId, id);
                    if (result > 0) {
                        successCount++;
                    } else {
                        failureCount++;
                    }
                } else {
                    lockedCount++;
                    continue;
                }
            } catch (Exception e) {
                failureCount++;
            } finally {
                LockUtil.unlock(id, userId);
            }
        }

        OrderDelResDto resDto = new OrderDelResDto();
        resDto.setFailureCount(failureCount);
        resDto.setLockedCount(lockedCount);
        resDto.setSuccessCount(successCount);
        return resDto;


    }
}
