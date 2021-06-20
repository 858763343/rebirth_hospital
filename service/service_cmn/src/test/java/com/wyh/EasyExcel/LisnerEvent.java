package com.wyh.EasyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class LisnerEvent extends AnalysisEventListener<UserDate> {

    /**
     * 一行一行读取Excel内容，从第二行读取
     * @param userDate
     * @param analysisContext
     */
    @Override
    public void invoke(UserDate userDate, AnalysisContext analysisContext) {
        System.out.println(userDate);
    }

    /**
     *  读取表头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息"+headMap);
    }

    /**
     * 读取之后执行
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
