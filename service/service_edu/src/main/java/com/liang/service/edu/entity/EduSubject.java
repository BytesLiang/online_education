package com.liang.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author liang
 * @since 2022-06-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@TableName("edu_subject")
@ApiModel(value = "EduSubject对象", description = "课程科目")
public class EduSubject extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类别名称")
    @TableField("title")
    private String title;

    @ApiModelProperty("父ID")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("排序字段")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("子分类")
    @TableField(exist = false)
    private List<EduSubject> children;

}
