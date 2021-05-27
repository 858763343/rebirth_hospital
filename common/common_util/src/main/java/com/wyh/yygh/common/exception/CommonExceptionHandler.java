package com.wyh.yygh.common.exception;

import com.wyh.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 设置统一的全局异常处理
 *
 * @author Timo
 * 2021-5-27
 */
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 系统的公共的异常提示
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result commonErro(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 自定义异常
     * 需手动抛出
     */
    @ResponseBody
    @ExceptionHandler(HospitalException.class)
    public Result hospSetException(HospitalException hospitalException){
        hospitalException.printStackTrace();
        return Result.fail();
    }

}
