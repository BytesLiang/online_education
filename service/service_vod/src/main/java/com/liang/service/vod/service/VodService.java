package com.liang.service.vod.service;

import com.liang.service.base.exceptionHandler.MyException;
import com.liang.service.vod.utils.VODClientUtils;
import org.springframework.stereotype.Service;

@Service
public class VodService {
    public String sign() {
        try{
            return VODClientUtils.signature.getUploadSignature();
        } catch (Exception e) {
            throw new MyException(20001, "获取签名失败");
        }
    }
    public void removeVideo(String fileId) {
        VODClientUtils.deleteMedia(fileId);
    }


}
