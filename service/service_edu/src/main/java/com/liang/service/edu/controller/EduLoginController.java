package com.liang.service.edu.controller;

import com.liang.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api("讲师管理")
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
public class EduLoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(){
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin");
        return Result.success(map);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result<Map<String, Object>> info(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(map);
    }
}
