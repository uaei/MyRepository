package com.opentext.poi.response;


import org.springframework.http.HttpStatus;


public final class ResponseModel<T>{

	private int code;
	private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseModel<T> ok(final T data) {
        ResponseModel<T> response = new ResponseModel<>();
        response.setCode(HttpStatus.OK.value());
        response.setMsg(HttpStatus.OK.getReasonPhrase());
        response.setData(data);
        return response;
    }

}
