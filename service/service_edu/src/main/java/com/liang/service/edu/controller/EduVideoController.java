package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduVideo;
import com.liang.service.edu.entity.vo.VideoVo;
import com.liang.service.edu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-07-06
 */
@Api(tags = "课程小节")
@RestController
@RequestMapping("/eduService/video")
@CrossOrigin
public class EduVideoController {

    private final EduVideoService videoService;

    public EduVideoController(EduVideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("根据id获取小节")
    @GetMapping("/{id}")
    public Result<EduVideo> getVideoById(@PathVariable String id){
        return Result.success(videoService.getById(id));
    }

    @ApiOperation("添加小节")
    @PostMapping("/add")
    public Result<Object> addVideo(@RequestBody EduVideo video){
        return videoService.save(video) ? Result.success() : Result.error();
    }

    @ApiOperation("修改小节")
    @PutMapping("/update")
    public Result<Object> updateVideo(@RequestBody VideoVo videoVo){
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoVo, eduVideo);
        return videoService.updateById(eduVideo) ? Result.success() : Result.error();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("/{id}")
    public Result<Object> deleteVideo(@PathVariable String id){
        return videoService.removeById(id) ? Result.success() : Result.error();
    }
}
