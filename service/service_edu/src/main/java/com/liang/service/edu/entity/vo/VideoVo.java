package com.liang.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {

    @ApiModelProperty("视频id")
    private String id;

    @ApiModelProperty("节点名称")
    private String title;
}
