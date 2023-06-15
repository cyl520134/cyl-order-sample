package com.cyl.example.dto;

import lombok.Data;

/**
 * @author changYL01
 * @date 2023/6/14 11:45
 * @description
 */
@Data
public class OrderDelResDto{
    /**
     * 成功数量
     */
    private int successCount;
    /**
     * 失败数量
     */
    private int failureCount;
    /**
     * 锁定数量
     */
    private int lockedCount;
}
