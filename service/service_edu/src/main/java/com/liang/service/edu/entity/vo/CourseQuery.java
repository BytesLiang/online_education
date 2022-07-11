package com.liang.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("状态 1 发布 0 未发布")
    private Integer status;

    @ApiModelProperty("大类")
    private String subjectParentId;

    @ApiModelProperty("小类")
    private String subjectId;

    @ApiModelProperty("排序方式 count 销售数量排序 price 价格排序 create 创建时间排序")
    private String sort = "create";

    @ApiModelProperty(value = "创建时间开始", example = "2021-01-01 10:23:34")
    private String begin;

    @ApiModelProperty(value = "创建时间终止", example = "2022-01-01 10:23:34")
    private String end;
}
