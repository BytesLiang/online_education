package com.liang.service.cos.controller;

import com.liang.common.utils.Result;
import com.liang.service.cos.service.CosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "cos存储")
@RestController
@RequestMapping("/eduCos/file")
@CrossOrigin
public class CosController {

    private final CosService cosService;

    public CosController(CosService cosService) {
        this.cosService = cosService;
    }

    @ApiOperation(("上传文件"))
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file){
        String url = cosService.uploadFileAvatar(file);
        return Result.success(url);
    }
}
