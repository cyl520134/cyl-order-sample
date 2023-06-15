package com.cyl.example.base.exception;

import com.cyl.example.base.ResCode;
import com.cyl.example.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(OrderException.class)
    public Result handleBusinessException(OrderException businessException, HttpServletResponse response) {
        logger.warn("业务异常 code = {}", businessException.getCode(), businessException);
        return new Result(false, businessException.getCode(), businessException.getMessage());
    }

    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    /**
     * 无效的请求。
     * status=400
     */
    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeException.class,
            ServletRequestBindingException.class,
            TypeMismatchException.class,
            HttpMessageConversionException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class})
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleBadRequestException(Exception e) {
        StringBuilder sb = new StringBuilder("无效的请求，");
        //warn 日志，不输出为 error日志。
        logger.warn("无效的请求：Exception", e);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            sb.append("错误内容：");
            for (ObjectError error : me.getBindingResult().getAllErrors()) {
                sb.append("[").append(error.getDefaultMessage()).append("] ");
            }
        }
        return new Result(false, ResCode.UNKNOWN_ERROR.getCode(), sb.toString());
    }

    @ExceptionHandler(Exception.class)
    public Result handleGlobalException(Exception exception, HttpServletResponse response) {
        logger.error("异常", exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Result(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception.getMessage());
    }

}
