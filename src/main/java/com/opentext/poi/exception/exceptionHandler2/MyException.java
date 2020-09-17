package com.opentext.poi.exception.exceptionHandler2;

/**
 * 编写自定义异常类：
 * @Author GuYaWei
 * @created 2020/9/17 15:50
 * @description：spring 对于 RuntimeException 异常才会进行事务回滚。。。
 */
public class MyException extends RuntimeException{


    private String code;

    private String msg;

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
