package com.liang.service.vod.utils;

import com.liang.service.base.exceptionHandler.MyException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Random;

@Data
@Component
@ConfigurationProperties(prefix = "tencent.vod")
public class VODClientUtils implements InitializingBean {

    private String secretId;
    private String secretKey;
    private String region;
    private String procedure;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String REGION;
    public static String PROCEDURE;

    public static Signature signature;
    public static VodClient vodClient;

    @Override
    public void afterPropertiesSet() {
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        REGION = region;
        PROCEDURE = procedure;
        signature = createSign();
        vodClient = createClient();
    }

    private static Signature createSign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(SECRET_ID);
        sign.setSecretKey(SECRET_KEY);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        sign.setProcedure(PROCEDURE);
        return sign;
    }
    private static VodClient createClient(){
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        Credential cred = new Credential(SECRET_ID, SECRET_KEY);
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("vod.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        return new VodClient(cred, "", clientProfile);
    }

    public static void deleteMedia(String fileId){
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(fileId);
            // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
            DeleteMediaResponse resp = vodClient.DeleteMedia(req);
            // 输出json格式的字符串回包
            System.out.println(DeleteMediaResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            throw new MyException(20001, "删除视频失败");
        }
    }
}
