package com.liang.service.cos.service.impl;

import com.liang.service.cos.service.CosService;
import com.liang.service.cos.utils.COSClientUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class CosServiceImpl implements CosService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        COSClient cosClient = COSClientUtils.createCOSClient();
        try{
            // 指定文件将要存放的存储桶
            String bucketName = COSClientUtils.BUCKET_NAME;
            // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String datePath = LocalDate.now().toString("yyyy/MM/dd");
            String key = "avatar/" + datePath + "/" + uuid + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key,
                    file.getInputStream(), new ObjectMetadata());
            cosClient.putObject(putObjectRequest);
            return "https://" + bucketName + ".cos." + COSClientUtils.REGION + ".myqcloud.com/" + key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            cosClient.shutdown();
        }
    }
}
