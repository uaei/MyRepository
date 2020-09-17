package com.opentext.poi.response;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public class RespJson {


	public static final String KEY_MESSAGE = "message";
	public static final String KEY_CODE = "code";
    public static final String TATA = "data";

	protected static HttpHeaders httpHeaders= new HttpHeaders();
	
	public RespJson() {
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}
	
	public static JSONObject getRespJson() {
		JSONObject respJson = new JSONObject();
		respJson.put(KEY_MESSAGE, HttpStatus.OK.getReasonPhrase());
		respJson.put(KEY_CODE, HttpStatus.OK.toString());
		return respJson;
	}


	public static <T> ResponseEntity respOK(T data){
        JSONObject respJson = new JSONObject();
        respJson.put(KEY_CODE, HttpStatus.OK.value());
        respJson.put(KEY_MESSAGE, HttpStatus.OK.getReasonPhrase());
        respJson.put(TATA,data);
        return new ResponseEntity<>(respJson.toJSONString(),httpHeaders,HttpStatus.OK);
    }

	
}
