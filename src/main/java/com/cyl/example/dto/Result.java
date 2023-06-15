package com.cyl.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;


/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
public final class Result<T> implements Serializable {

    /**
     * 提供三种常用的错误码前缀：前列建议各业务自行分开自己的前缀代号，方便查问题
     */
    public static final String CLI_PREFIX = "cli:";
    public static final String SYS_PREFIX = "sys:";
    public static final String SERVER_ERR_CODE = SYS_PREFIX + "500";
    public static final String SERVER_ERR_MSG = "system unknown error";
    public static final String PARAMETER_INVALID_CODE = SYS_PREFIX + "000";

    //****************通用的错误返回码********************
    public static final String PARAMETER_INVALID_MSG = "parameter valid failed";
    // 业务逻辑校验异常（一般均为手动抛出，提供给外部访问）
    public static final String SERVICE_INVALID_CODE = SYS_PREFIX + "111";
    public static final String SERVICE_INVALID_MSG = "service is invalid"; // Precondition Failed
    private boolean success = true;
    private T data = null;
    private Long timestamp = Instant.now().toEpochMilli();
    /**
     * 此时success = false
     * 当出现错误时，一般需完善此部分，给与良好的业务代码展示
     */
    private String code;
    private String message;

    // 实例请使用静态方法来创建
    private Result() {
    }

    public Result(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    //****************静态方法们********************
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    public static <D> Result ok(D data) {
        Result<D> result = ok();
        result.setData(data);
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(SERVER_ERR_CODE);
        result.setMessage(SERVER_ERR_MSG);
        return result;
    }

    public static Result fail(String errCode, String errMsg) {
        Result result = fail();
        result.setCode(errCode);
        result.setMessage(errMsg);
        return result;
    }


    //********************************************************************
    public Result setSuccess(boolean success) {
        this.success = success;
        if (success) {
            this.setMessage(null);
            this.setCode(null);
        } else {
            this.setData(null);
        }
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 刷新为最新的时刻
     */
    public Result refreshTimestamp() {
        this.timestamp = Instant.now().toEpochMilli();
        return this;
    }
}
