package com.liang.service.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.service.base.exceptionHandler.MyException;
import com.liang.common.utils.Result;
import com.liang.service.edu.entity.EduTeacher;
import com.liang.service.edu.entity.vo.TeacherQuery;
import com.liang.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/eduService/teacher")
@CrossOrigin
public class EduTeacherController {

    private final EduTeacherService teacherService;

    public EduTeacherController(EduTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation("查询所有讲师")
    @GetMapping("/list")
    public Result<List<EduTeacher>> fidAllTeacher(){
        List<EduTeacher> teachers = teacherService.list(null);
        return Result.success(teachers);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Result<EduTeacher> getTeacher(@PathVariable String id){
        return Result.success(teacherService.getById(id));
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("/{id}")
    public Result<Object> removeTeacher(@ApiParam(name="id", value = "讲师id", required = true) @PathVariable String id){
        return teacherService.removeById(id) ? Result.success() : Result.error();
    }

    @ApiOperation("分页查询")
    @GetMapping("/page/{current}/{limit}")
    public Result<Map<String, Object>> pageListTeacher(@PathVariable long current,
                                                    @PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        teacherService.page(teacherPage, null);
        Map<String, Object> map = new HashMap<>();
        map.put("total", teacherPage.getPages());
        map.put("rows", teacherPage.getRecords());
        return Result.success(map);
    }

    @ApiOperation("条件查询")
    @PostMapping("/page/{current}/{limit}")
    public Result<Map<String, Object>> pageTeacherCondition(@PathVariable long current,
                                       @PathVariable long limit,
                                       @RequestBody TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(name), "name", name);
        queryWrapper.eq(!StringUtils.isEmpty(level), "level", level);
        queryWrapper.ge(!StringUtils.isEmpty(begin), "join_date", begin);
        queryWrapper.le(!StringUtils.isEmpty(end), "join_date", end);
        queryWrapper.orderByDesc("gmt_create");
        teacherService.page(teacherPage, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("total", teacherPage.getTotal());
        map.put("rows", teacherPage.getRecords());
        return Result.success(map);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/add")
    public Result<Object> addTeacher(@RequestBody EduTeacher eduTeacher){
        return teacherService.save(eduTeacher) ? Result.success() : Result.error();
    }

    @ApiOperation("修改讲师")
    @PutMapping("/update")
    public Result<Object> updateTeacher(@RequestBody EduTeacher teacher){
        return teacherService.updateById(teacher) ? Result.success() : Result.error();
    }

    @ApiOperation("根据id修改讲师")
    @PostMapping("/{id}")
    public Result<Object> updateTeacherById(@PathVariable String id, @RequestBody EduTeacher teacher){
        teacher.setId(id);
        return teacherService.updateById(teacher) ? Result.success() : Result.error();
    }

    @ApiOperation("异常处理测试")
    @PostMapping("/exception")
    public Result<Object> testException(){
        try{
            int i = 3/0;
        } catch (Exception e){
            throw new MyException(20001, "MyException");
        }
        return Result.success();
    }

}
