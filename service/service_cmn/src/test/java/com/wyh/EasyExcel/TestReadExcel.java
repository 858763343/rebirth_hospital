package com.wyh.EasyExcel;


import com.alibaba.excel.EasyExcel;

public class TestReadExcel {

    public static void main(String[] args) {
        // 设置读取文件路径
        String fileName = "D:\\excel\\01.xlsx";

        // 调用方法执行读取操作
        EasyExcel.read(fileName,UserDate.class,new LisnerEvent()).doReadAll();
    }

}
