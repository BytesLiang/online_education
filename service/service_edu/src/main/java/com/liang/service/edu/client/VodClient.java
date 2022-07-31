package com.liang.service.edu.client;

import com.liang.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "server-vod", fallback = VodDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping("/edu/vod/{fileId}")
    Result<Object> remove(@PathVariable("fileId") String fileId);

    @DeleteMapping("/edu/vod/delete")
    Result<Object> removeBatch(@RequestParam("videoList") List<String> videoList);
}
