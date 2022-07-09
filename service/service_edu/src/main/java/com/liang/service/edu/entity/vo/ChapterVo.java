package com.liang.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {

    @ApiModelProperty("章节id")
    private String id;

    @ApiModelProperty("章节名称")
    private String title;


    @ApiModelProperty("显示排序")
    private Integer sort;

    private List<VideoVo> children = new ArrayList<>();
}
