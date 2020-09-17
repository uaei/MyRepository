package com.opentext.poi.exception.exceptionHandler2;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ： GuYaWei
 * @created ： 2020/9/17 15:58
 * @description ：controller增强器
 */
@RestControllerAdvice
public class MyRestControllerAdvice {

    /**
     * 全局异常捕获处理。
     * @param ex
     */
    @ExceptionHandler(Exception.class)
    public Map errorHandler(Exception ex){
        Map map = new HashMap<>();
        map.put("code",100);
        map.put("msg",ex.getMessage());
        return map;
    }


    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ExceptionHandler({MyException.class})
    public Map myErrorHandler(MyException ex){
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }
}
