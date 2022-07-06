package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.vo.ChapterVo;
import com.liang.service.edu.service.EduChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/eduService/chapter")
public class EduChapterController {

    private final EduChapterService chapterService;

    public EduChapterController(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/{courseId}")
    public Result<List<ChapterVo>> getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideo(courseId);
        return Result.success(list);
    }
}
