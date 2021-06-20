package com.wyh.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyh.hospital.model.hosp.HospitalSet;
import com.wyh.hospital.vo.order.SignInfoVo;

public interface HospitalSetService extends IService<HospitalSet> {

    //2 根据传递过来医院编码，查询数据库，查询签名
    String getSignKey(String hoscode);

    //获取医院签名信息
    SignInfoVo getSignInfoVo(String hoscode);
}
