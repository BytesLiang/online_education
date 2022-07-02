package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduSubject;
import com.liang.service.edu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-06-30
 */
@Api(tags = "课程分类")
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin
public class EduSubjectController {

    private final EduSubjectService subjectService;

    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.subjectService = eduSubjectService;
    }

    @ApiOperation("添加课程分类")
    @PostMapping("/add")
    public Result<Object> addSubject(@RequestPart("file") MultipartFile file) {
        subjectService.saveSubject(file, subjectService);
        return Result.success();
    }

    @ApiOperation("查询课程分类列表")
    @GetMapping("/list")
    public Result<List<EduSubject>> getSubjectList() {
        List<EduSubject> subjectList = subjectService.getAllSubject();
        return Result.success(subjectList);
    }
}