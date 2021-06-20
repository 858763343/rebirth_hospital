package com.wyh.EasyExcel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestWiteExcel {

    public static void main(String[] args) {
        // 设置文件路径
        String filePath ="D:\\excel\\01.xlsx";

        List<UserDate> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            UserDate userDate = new UserDate();
            userDate.setId(i);
            userDate.setUserName("张三"+i);
            list.add(userDate);
        }
        // 调用EasyExcel 方法
        EasyExcel.write(filePath, UserDate.class).sheet("用户信息").doWrite(list);
    }


}
