package com.liang.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Result<T> {

    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    // 构造方法私有化
    private Result(){}

    public static <T> Result<T> success(){
        return new Result<T>()
                .setSuccess(true)
                .setCode(ResultCode.SUCCESS.getType())
                .setMessage("success");
    }

    public static <T> Result<T> error(){
        return new Result<T>()
                .setSuccess(false)
                .setCode(ResultCode.ERROR.getType())
                .setMessage("error");
    }
}
