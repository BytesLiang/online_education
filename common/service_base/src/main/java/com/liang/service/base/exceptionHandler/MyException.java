package com.liang.service.base.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("异常信息")
    private String msg;
}
