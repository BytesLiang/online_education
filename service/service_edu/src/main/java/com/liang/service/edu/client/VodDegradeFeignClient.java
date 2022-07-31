package com.liang.service.edu.client;

import com.liang.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodDegradeFeignClient implements VodClient {
    @Override
    public Result<Object> remove(String fileId) {
        return Result.error().setMessage("删除视频出错了");
    }

    @Override
    public Result<Object> removeBatch(List<String> videoList) {
        return Result.error().setMessage("删除视频出错了");
    }
}
