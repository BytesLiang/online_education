package com.liang.service.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.common.utils.Result;
import com.liang.service.edu.entity.CourseStatus;
import com.liang.service.edu.entity.EduCourse;
import com.liang.service.edu.entity.vo.CourseInfo;
import com.liang.service.edu.entity.vo.CoursePublishVo;
import com.liang.service.edu.entity.vo.CourseQuery;
import com.liang.service.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Api(tags = "课程信息")
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

    @ApiOperation("课程分页条件查询")
    @PostMapping("/page/{current}/{limit}")
    public Result<Map<String, Object>> pageListCourse(@PathVariable long current,
                                                      @PathVariable long limit,
                                                      @RequestBody CourseQuery courseQuery) {
        Map<String, Object> map = courseService.queryByCondition(current, limit, courseQuery);
        return Result.success(map);
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

    @ApiOperation("查询课程确认信息")
    @GetMapping("/publish/{id}")
    public Result<CoursePublishVo> getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo courseInfo = courseService.getPublishCourseInfo(id);
        return Result.success(courseInfo);
    }

    @ApiOperation("发布课程")
    @PostMapping("/publish/{id}")
    public Result<Object> publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(CourseStatus.NORMAL.getStatus());
        return courseService.updateById(eduCourse) ? Result.success() : Result.error().setMessage("课程发布失败");
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/{id}")
    public Result<Object> deleteCourse(@PathVariable String id){
        courseService.removeCourse(id);
        return Result.success();
    }
}
