package com.liang.service.base.exceptionHandler;

import com.liang.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<Object> error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().setMessage("GlobalException");
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result<Object> error(ArithmeticException e){
        log.error(e.getMessage());
        return Result.error().setMessage("ArithmeticException");
    }

    @ExceptionHandler(MyException.class)
    public Result<Object> error(MyException e){
        log.error(e.getMessage());
        return Result.error()
                .setCode(e.getCode())
                .setMessage(e.getMsg());
    }
}
