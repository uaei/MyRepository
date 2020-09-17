package com.opentext.lambda.demo;

public class DefaultableImpl implements Defaulable {

    public static void main(String[] args) {
        DefaultableImpl defaultable = new DefaultableImpl();
        String s = defaultable.notRequired();
        System.out.println(s);//Default implementation
    }
}