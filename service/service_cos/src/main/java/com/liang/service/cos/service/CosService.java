package com.liang.service.cos.service;


import org.springframework.web.multipart.MultipartFile;

public interface CosService {
    // 上传头像
    String uploadFileAvatar(MultipartFile file);
}
