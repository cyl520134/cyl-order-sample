package com.cyl.example.dao;


import com.cyl.example.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@Repository
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo orderInfo);

    OrderInfo selectByPrimaryKey(Long id);

    OrderInfo selectByUserIdAndId(@Param("userId") Long userId, @Param("id") Long id);

    int delByUserIdAndId(@Param("userId") Long userId, @Param("id") Long id);

    int updateByPrimaryKeySelective(OrderInfo orderInfo);
}
