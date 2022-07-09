package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduChapter;
import com.liang.service.edu.entity.vo.ChapterVo;
import com.liang.service.edu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
    @GetMapping("/list/{courseId}")
    public Result<List<ChapterVo>> getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideo(courseId);
        return Result.success(list);
    }

    @ApiOperation("根据id查询章节信息")
    @GetMapping("/{chapterId}")
    public Result<EduChapter> getChapter(@PathVariable String chapterId){
        return Result.success(chapterService.getById(chapterId));
    }

    @ApiOperation("添加课程章节")
    @PostMapping("/add")
    public Result<Object> addChapter(@RequestBody EduChapter chapter){
        return chapterService.save(chapter) ? Result.success() : Result.error();
    }

    @ApiOperation("修改课程章节")
    @PostMapping("/update")
    public Result<Object> updateChapter(@RequestBody ChapterVo chapterVo){
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(chapterVo, eduChapter);
        return chapterService.updateById(eduChapter) ? Result.success() : Result.error();
    }

    @ApiOperation("删除课程章节")
    @DeleteMapping("/{chapterId}")
    public Result<Object> deleteChapter(@PathVariable String chapterId){
        return chapterService.deleteChapter(chapterId) ? Result.success() : Result.error();
    }
}
