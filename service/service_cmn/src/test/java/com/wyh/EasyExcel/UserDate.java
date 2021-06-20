package com.wyh.EasyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserDate {

    @ExcelProperty(value = "用户编码",index = 0)
    private  int id;

    @ExcelProperty(value = "用户姓名",index = 1)
    private  String userName;


}
