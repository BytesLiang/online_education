package com.liang.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePublishVo {

    @ApiModelProperty("课程id")
    private String id;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @ApiModelProperty("总课时")
    private Integer lessonNum;

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty("课程专业父级")
    private String subjectLevelOne;

    @ApiModelProperty("课程专业")
    private String subjectLevelTwo;

    @ApiModelProperty("课程讲师")
    private String teacherName;

    @ApiModelProperty("课程简介")
    private String description;
}
