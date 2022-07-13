package com.liang.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {

    @ApiModelProperty("视频id")
    private String id;

    @ApiModelProperty("节点名称")
    private String title;

    @ApiModelProperty("显示排序")
    private Integer sort;

    @ApiModelProperty("云端视频资源")
    private String videoSourceId;

    @ApiModelProperty("原始文件名称")
    private String videoOriginalName;

    @ApiModelProperty("是否可以试听：0收费 1免费")
    private Boolean free;
}
