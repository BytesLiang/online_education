package com.liang.service.vod.controller;

import com.liang.common.utils.Result;
import com.liang.service.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "视频点播")
@RestController
@RequestMapping("/edu/vod")
@CrossOrigin
public class VodController {

    private final VodService vodService;

    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    @ApiOperation("生成上传签名")
    @GetMapping("/sign")
    public Result<String> sign() {
        return Result.success(vodService.sign());
    }

    @ApiOperation("删除视频")
    @DeleteMapping("/{fileId}")
    public Result<Object> remove(@PathVariable String fileId){
        vodService.removeVideo(fileId);
        return Result.success();
    }

    @ApiOperation("批量删除视频")
    @DeleteMapping("delete")
    public Result<Object> removeBatch(@RequestParam("videoList") List<String> videoList){
        vodService.removeBatch(videoList);
        return Result.success();
    }
}
