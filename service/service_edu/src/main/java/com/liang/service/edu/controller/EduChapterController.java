package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.vo.ChapterVo;
import com.liang.service.edu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Api(tags = "课程章节信息")
@RestController
@RequestMapping("/eduService/chapter")
@CrossOrigin
public class EduChapterController {

    private final EduChapterService chapterService;

    public EduChapterController(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @ApiOperation("获取课程章节信息")
    @GetMapping("/{courseId}")
    public Result<List<ChapterVo>> getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideo(courseId);
        return Result.success(list);
    }
}
