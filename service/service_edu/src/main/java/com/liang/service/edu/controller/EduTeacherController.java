package com.liang.service.edu.controller;

import com.liang.service.edu.entity.EduTeacher;
import com.liang.service.edu.mapper.EduTeacherMapper;
import com.liang.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-06-22
 */
@Api(tags = "讲师信息处理")
@RestController
@RequestMapping("/service/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation("查询所有讲师")
    @GetMapping("/findAll")
    public List<EduTeacher> fidAllTeacher(){
        return teacherService.list(null);
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("{id}")
    public boolean removeTeacher(@ApiParam(name="id", value = "讲师id", required = true) @PathVariable String id){
        return teacherService.removeById(id);
    }

}
