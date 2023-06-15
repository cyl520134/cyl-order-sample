package com.cyl.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@SpringBootApplication
@MapperScan({"com.cyl.example.dao"})
public class CylOrderSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CylOrderSampleApplication.class, args);
    }

}
