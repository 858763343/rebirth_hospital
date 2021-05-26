package com.wyh.yygh.hosp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyh.hospitol.model.hosp.HospitalSet;
import com.wyh.hospitol.vo.hosp.HospitalSetQueryVo;
import com.wyh.yygh.common.result.Result;
import com.wyh.yygh.hosp.service.HosptialSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * hosptialset
 */
@RestController
@Api(tags = "医院设置")
@RequestMapping("admin/hosp/hospitalSet")
public class HosptialSetController {

    @Autowired
    private HosptialSetService hosptialSetService;


    /**
     * 查询医院设置所有信息
     *
     * @return Resutl 返回统一结果 的工具类
     * localhost:8201/admin/hosp/hospitalSet/findAll
     */
    @ApiOperation(value = "查询医院设置所有信息")
    @GetMapping("findAll")
    public Result findAllHospSet() {

        List<HospitalSet> list = hosptialSetService.list();
        return Result.ok(list);

    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
    public Result deleteHospSet(@PathVariable Long id) {
        boolean tof = hosptialSetService.removeById(id);
        //判断是否成功
        if (tof) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    /**
     * 条件查询带分页
     * 根据编号查询
     * 根据名称查询
     * current 当前页
     * limit 显示条数
     * 以json格式进行传入参数
     */
    @ApiOperation(value = "条件分页查询")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo
    ) {
        //创建Page 对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);
        String hosname = hospitalSetQueryVo.getHosname();   //医院的名称
        String hoscode = hospitalSetQueryVo.getHoscode();   //医院的编号
        //构造条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        //判断 名称和编号是否有值
        if (!StringUtils.isEmpty(hosname)) {
            queryWrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)) {
            queryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }
        //调用方法实现分页的查询
        Page<HospitalSet> setPage = hosptialSetService.page(page, queryWrapper);
        return Result.ok(setPage);
    }

    /***
     * 添加医院设置
     */

    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospSet")
    public Result saveHospSet(@RequestBody HospitalSet hospitalSet) {


        boolean save = hosptialSetService.save(hospitalSet);
        if (save) {
           return Result.ok();
        } else {
          return  Result.fail();
        }

    }


}
