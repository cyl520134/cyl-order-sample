package com.cyl.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserInfo {
    private Long id;
    private String userName;
    private String password;
    private Date registerDate;
}