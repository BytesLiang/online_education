package com.liang.service.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduTeacher;
import com.liang.service.edu.entity.vo.TeacherQuery;
import com.liang.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<List<EduTeacher>> fidAllTeacher(){
        List<EduTeacher> teachers = teacherService.list(null);
        return Result.<List<EduTeacher>> success().setData(teachers);
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("{id}")
    public Result<Boolean> removeTeacher(@ApiParam(name="id", value = "讲师id", required = true) @PathVariable String id){
        return teacherService.removeById(id) ? Result.success() : Result.error();
    }

    @ApiOperation("分页查询")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Result<Map<String, Object>> pageListTeacher(@PathVariable long current,
                                                    @PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        teacherService.page(teacherPage, null);
        Map<String, Object> map = new HashMap<>();
        map.put("total", teacherPage.getPages());
        map.put("rows", teacherPage.getRecords());
        return Result.<Map<String, Object>> success().setData(map);
    }

    @ApiOperation("条件查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result<Map<String, Object>> pageTeacherCondition(@PathVariable long current,
                                       @PathVariable long limit,
                                       @RequestBody TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(name), "name", name);
        queryWrapper.eq(!StringUtils.isEmpty(level), "level", level);
        queryWrapper.ge(!StringUtils.isEmpty(begin), "gmt_create", begin);
        queryWrapper.le(!StringUtils.isEmpty(end), "gmt_create", end);
        teacherService.page(teacherPage, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("total", teacherPage.getPages());
        map.put("rows", teacherPage.getRecords());
        return Result.<Map<String, Object>> success().setData(map);
    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public Result<Object> addTeacher(@RequestBody EduTeacher eduTeacher){
        return teacherService.save(eduTeacher) ? Result.success() : Result.error();
    }
}
