package com.liang.service.cos.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tencent.cos")
public class COSClientUtils implements InitializingBean {

    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String REGION;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() {
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        REGION = region;
        BUCKET_NAME = bucketName;
    }

    public static COSClient createCOSClient(){
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        // 2 设置 bucket 的地域
        Region region = new Region(REGION);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }
}
