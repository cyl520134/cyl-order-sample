package com.cyl.example.base.exception;

/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public class LockingFailureException extends OrderException {
    public LockingFailureException(String errMsg) {
        super(errMsg);
    }

    public LockingFailureException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
