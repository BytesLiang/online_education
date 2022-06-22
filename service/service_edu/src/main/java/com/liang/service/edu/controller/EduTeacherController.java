package com.liang.service.edu.controller;

import com.liang.service.edu.entity.EduTeacher;
import com.liang.service.edu.mapper.EduTeacherMapper;
import com.liang.service.edu.service.EduTeacherService;
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
@RestController
@RequestMapping("/service/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    // 查询所有讲师
    @GetMapping("/findAll")
    public List<EduTeacher> fidAllTeacher(){
        return teacherService.list(null);
    }

    // 删除讲师
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id){
        return teacherService.removeById(id);
    }

}
