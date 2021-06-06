package com.wyh.yygh.cmn.controller;

import com.wyh.hospitol.model.cmn.Dict;
import com.wyh.yygh.cmn.service.DictService;
import com.wyh.yygh.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cmn dict
 * 数据字典
 * @CrossOrigin
 * 跨域
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 根据数据id查询子数据列表
     */
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("finChildDateById/{id}")
    public Result finChildDateById(@PathVariable Long id){
       List<Dict> childDatelist = dictService.finChildDateById(id);
        return Result.ok(childDatelist);
    }


}
