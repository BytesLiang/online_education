package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduCourse;
import com.liang.service.edu.entity.vo.CourseInfo;
import com.liang.service.edu.service.EduCourseService;
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
@Api("课程信息")
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
public class EduCourseController {

    private final EduCourseService courseService;

    public EduCourseController(EduCourseService eduCourseService) {
        this.courseService = eduCourseService;
    }

    @ApiOperation("课程列表")
    @GetMapping()
    public Result<List<EduCourse>> getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return Result.success(list);
    }

    @ApiOperation("添加课程信息")
    @PostMapping("/add")
    public Result<String> addCourseInfo(@RequestBody CourseInfo courseInfo){
        String id = courseService.saveCourseInfo(courseInfo);
        return Result.success(id);
    }

    @ApiOperation("获取课程信息")
    @GetMapping("/{courseId}")
    public Result<CourseInfo> getCourseInfo(@PathVariable String courseId) {
        CourseInfo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.success(courseInfoVo);
    }

    @ApiOperation("修改课程信息")
    @PutMapping("/update")
    public Result<Object> updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        courseService.updateCourseInfo(courseInfo);
        return Result.success();
    }
}
