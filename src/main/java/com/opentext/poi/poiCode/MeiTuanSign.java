package com.opentext.poi.poiCode;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

public class MeiTuanSign {

    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("appkey","fe7c341e1d2f7edf76b77e18c6f941cd757"); //申请的 appkey
//        map.put("pos","30.4561282109,114.4291681051");
        map.put("city","北京");
        map.put("offset","3");
        map.put("limit","25");
        map.put("query","学校");
        map.put("devicetype","android");
//        map.put("cateid","20286&20289&20290&20291&20292&20293&20294&21358&21444&21458&21462&21467&21472&21473&21477&21480&20333&20334&20335&20336&21461");
        StringBuilder s = new StringBuilder();
        for(Map.Entry<String, String> entry :map.entrySet())
        {
            s.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        String params = s.toString();
        s.append("c028c4d8bffbe2bdd5cc9461d2cbbaad"); //分配的 secret
        String encrypt = DigestUtils.sha256Hex(s.toString().getBytes()).toLowerCase();
        String url = "http://openapi.meituan.com/poiCode/search?" + params + "sign=" + encrypt;
        System.out.println(url);
    }

}
