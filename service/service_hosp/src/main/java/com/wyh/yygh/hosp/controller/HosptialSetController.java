package com.wyh.yygh.hosp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyh.hospitol.model.hosp.HospitalSet;
import com.wyh.hospitol.vo.hosp.HospitalSetQueryVo;
import com.wyh.yygh.common.result.Result;
import com.wyh.yygh.common.utils.MD5;
import com.wyh.yygh.hosp.service.HosptialSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * hosptialset
 *
 * @author Timo
 * 2021/5/27
 */
@RestController
@Api(tags = "医院设置")
@RequestMapping("admin/hosp/hospitalSet")
@CrossOrigin
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
        //构造条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();   //医院的名称
        String hoscode = hospitalSetQueryVo.getHoscode();   //医院的编号
        //判断 名称和编号是否有值
        if (!StringUtils.isEmpty(hosname)) {
            queryWrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)) {
            queryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }
        //调用方法实现分页的查询
        IPage<HospitalSet> setPage = hosptialSetService.page(page, queryWrapper);
        return Result.ok(setPage);
    }

    /***
     * 添加医院设置
     */

    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospSet")
    public Result saveHospSet(@RequestBody HospitalSet hospitalSet) {
        //设置status 状态  0不能使用 1 可以使用
        hospitalSet.setStatus(1);
        //签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //调用sevice 方法
        boolean save = hosptialSetService.save(hospitalSet);
        if (save) {
           return Result.ok();
        } else {
          return  Result.fail();
        }

    }

    /***
     * 根据id获取医院设置
     */
    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("findHospSetById/{id}")
    public Result findHospSetById(@PathVariable Long id){

        HospitalSet findHospSetById = hosptialSetService.getById(id);
        return Result.ok(findHospSetById);
    }

    /***
     * 修改医院设置
     */

    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospSet")
    public Result updateHospSet(@RequestBody HospitalSet hospitalSet) {

        boolean updateById = hosptialSetService.updateById(hospitalSet);
        if (updateById){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * 批量删除医院设置
     */
        @ApiOperation(value = "批量删除医院设置")
        @DeleteMapping("DeleteHospSetByids")
        public Result DeleteHospSetByids(@RequestBody List<Long> idList){
                hosptialSetService.removeByIds(idList);
                return Result.ok();
        }

    /**
     * 医院设置锁定和解锁
     */

    @ApiOperation(value = "锁定")
    @PutMapping(value = "LockHospSet/{id}/{status}")
    public Result LockHospSet(@PathVariable Long id,
                              @PathVariable Integer status){
        //根据id 查询医院的设置信息
        HospitalSet hospitalSet = hosptialSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用根据id修改的方法 将设置的 status 进行更新
        hosptialSetService.updateById(hospitalSet);
        return Result.ok();
    }

    /***
     * 发送签名密钥
     */
    @ApiOperation(value = "发送签名密钥")
    @PutMapping(value = "sendKeyHospSet/{id}")
    public Result sendKeyHospSet(@PathVariable Long id) {
        //先根据id 查询
        HospitalSet byId = hosptialSetService.getById(id);
        //通过查询出的值获取 密钥和医院编号
        String signKey = byId.getSignKey();     //获取签名密钥
        String hoscode = byId.getHoscode();     //获取医院编号
        //TODO 发送短信
        return Result.ok();
    }

}
