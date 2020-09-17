package com.opentext.lambda.demo_1;

/**
 * @Author GuYaWei
 * @created 2020/8/11 15:26
 * @description
 */
public class User {


    private int num;

    private String code;

    private String adderss;

    private String name;

    public User(int num, String code, String adderss, String name) {
        this.num = num;
        this.code = code;
        this.adderss = adderss;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "num=" + num + ", code='" + code + '\'' + ", adderss='" + adderss + '\'' + ", name='" + name + '\'' + '}';
    }
}
