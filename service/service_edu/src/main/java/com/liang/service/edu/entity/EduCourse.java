package com.liang.service.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liang.service.edu.entity.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("edu_course")
@ApiModel(value = "EduCourse对象", description = "课程")
public class EduCourse extends BaseEntity {

    @ApiModelProperty("课程讲师ID")
    @TableField("teacher_id")
    private String teacherId;

    @ApiModelProperty("课程专业ID")
    @TableField("subject_id")
    private String subjectId;

    @ApiModelProperty("课程专业父级ID")
    @TableField("subject_parent_id")
    private String subjectParentId;

    @ApiModelProperty("课程标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    @TableField("lesson_num")
    private Integer lessonNum;

    @ApiModelProperty("课程封面图片路径")
    @TableField("cover")
    private String cover;

    @ApiModelProperty("销售数量")
    @TableField("buy_count")
    private Long buyCount;

    @ApiModelProperty("浏览数量")
    @TableField("view_count")
    private Long viewCount;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    private Long version;

    @ApiModelProperty("课程状态 0未发布  1已发布")
    @TableField("status")
    private Boolean status;


}
