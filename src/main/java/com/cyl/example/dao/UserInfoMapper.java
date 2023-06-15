package com.cyl.example.dao;


import com.cyl.example.entity.OrderInfo;
import com.cyl.example.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo orderInfo);

    UserInfo selectByPrimaryKey(Long id);

    int countByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);


}
