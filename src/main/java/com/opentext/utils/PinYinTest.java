package com.opentext.utils;

import static com.opentext.utils.PinyinDemo.ToFirstChar;
import static com.opentext.utils.PinyinDemo.ToPinyin;

/**
 * @Author GuYaWei
 * @created 2020/8/13 10:31
 * @description
 */
public class PinYinTest {

    public static void main(String[] args) {

        //转为首字母大写
        System.out.println(ToFirstChar("北京").toUpperCase());
        System.out.println(ToPinyin("北京"));
    }
}
