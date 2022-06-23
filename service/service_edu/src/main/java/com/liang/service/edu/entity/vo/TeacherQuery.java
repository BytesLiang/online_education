package com.liang.service.edu.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TeacherQuery implements Serializable {

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:23:34")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2022-01-01 10:23:34")
    private String end;
}
