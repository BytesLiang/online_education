package com.liang.service.edu.controller.front;

import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduCourse;
import com.liang.service.edu.entity.EduTeacher;
import com.liang.service.edu.service.EduCourseService;
import com.liang.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "前台首页")
@RestController
@RequestMapping("/eduService/front/index")
@CrossOrigin
public class IndexController {

    private final EduCourseService courseService;
    private final EduTeacherService teacherService;

    public IndexController(EduCourseService courseService, EduTeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @ApiOperation("首页显示课程")
    @GetMapping("/course/{count}")
    public Result<List<EduCourse>> selectCourseIndex(@PathVariable String count){
        return Result.success(courseService.selectCourse(count));
    }

    @ApiOperation("首页显示讲师")
    @GetMapping("/teacher/{count}")
    public Result<List<EduTeacher>> selectTeacherIndex(@PathVariable String count){
        return Result.success(teacherService.selectTeacher(count));
    }
}
