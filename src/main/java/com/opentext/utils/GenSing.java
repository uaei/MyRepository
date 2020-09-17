package com.opentext.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author GuYaWei
 * @created 2020/7/6 10:19
 * @description
 */
public class GenSing {

    public static String genSign(TreeMap<String, String> map) {

        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            s.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String params = s.toString();
        //分配的 secret
        s.append("c028c4d8bffbe2bdd5cc9461d2cbbaad");
        String encrypt = DigestUtils.sha256Hex(s.toString().getBytes()).toLowerCase();
        String url = "http://openapi.meituan.com/poiCode/search?" + params + "sign：" + encrypt;
        return url;
    }
}
