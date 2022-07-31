package com.liang.service.cms.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BannerVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("链接地址")
    private String linkUrl;

    @ApiModelProperty("排序")
    private Integer sort;
}
