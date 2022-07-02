package com.liang.service.edu.excel;


import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    String fileName = "D://test.xlsx";

    @Test
    public void simpleWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    @Test
    public void simpleRead(){
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    public static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            DemoData data = new DemoData(i, "lucy"+i);
            list.add(data);
        }
        return list;
    }
}
