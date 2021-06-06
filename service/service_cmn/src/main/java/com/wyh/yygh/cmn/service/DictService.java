package com.wyh.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyh.hospitol.model.cmn.Dict;

import java.util.List;

/**
 * 数据字典 接口
 */
public interface DictService extends IService<Dict> {

    List<Dict> finChildDateById(Long id);
}
