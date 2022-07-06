package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.vo.CourseInfo;
import com.liang.service.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Api("课程信息")
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
public class EduCourseController {

    private final EduCourseService courseService;

    public EduCourseController(EduCourseService eduCourseService) {
        this.courseService = eduCourseService;
    }

    @ApiOperation("添加课程信息")
    @PostMapping("/add")
    public Result<String> addCourseInfo(@RequestBody CourseInfo courseInfo){
        String id = courseService.saveCourseInfo(courseInfo);
        return Result.success(id);
    }
}
