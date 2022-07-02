package com.liang.service.cos.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class COSClientUtilsTest {

    @Autowired
    private COSClientUtils cosClientUtils;

    @Test
    public void test(){
        System.out.println(COSClientUtils.BUCKET_NAME);
    }

}