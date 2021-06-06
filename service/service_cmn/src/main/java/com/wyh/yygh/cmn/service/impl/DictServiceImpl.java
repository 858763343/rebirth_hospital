package com.wyh.yygh.cmn.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyh.hospitol.model.cmn.Dict;
import com.wyh.yygh.cmn.mapper.DictMapper;
import com.wyh.yygh.cmn.service.DictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cmnimpl
 *  实现数据字典 接口
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    //根据数据id查询子数据列表
    @Override
    public List<Dict> finChildDateById(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        //遍历list 中的数据 向list集合每个dict对象中设置 hasChild对象
        for(Dict dict:dictList){
            Long dictId = dict.getId();
            boolean ischild = this.isChild(dictId);  //调用isChild方法 判断是否有子节点
            dict.setHasChildren(ischild);            //将 判断的结果传给 Dict 对象中的 hasChild结果中
        }

        return dictList;
    }

    //判断id 下面是否有字节点
    private boolean isChild(Long id){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        //若节点数大于0 则 Dict 中的 hasChild 为 true 否则 为 false
        return count>0;
    }
}




