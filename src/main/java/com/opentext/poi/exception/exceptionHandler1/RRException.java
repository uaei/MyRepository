package com.opentext.poi.exception.exceptionHandler1;

/**
 * @Author GuYaWei
 * @created 2020/9/15 17:22
 * @description
 */
public class RRException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    private int code = 500;

    private String msg;


    public RRException() {
    }

    public RRException(String msg) {
        this.msg = msg;
    }

    public RRException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RRException(String message, Throwable cause) {
        super(message, cause);
    }

    public RRException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
