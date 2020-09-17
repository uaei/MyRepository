package com.opentext.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * sign加密
 */
public class Sign {

    public static String genSign(TreeMap<String,String> params) {

        StringBuffer s = new StringBuffer();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> item = it.next();
            s.append(item.getKey()).append("=").append(item.getValue()).append("&");
        }
        String generateSign = DigestUtils.sha256Hex(s.toString().getBytes()).toLowerCase();
        return generateSign;
    }

}
