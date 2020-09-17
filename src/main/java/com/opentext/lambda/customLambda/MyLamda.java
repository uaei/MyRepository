package com.opentext.lambda.customLambda;

/**
 * @Author GuYaWei
 * @created 2020/7/14 11:09
 * @description
 */
@FunctionalInterface
public interface MyLamda {

    public abstract void test(String y);

    //这里如果继续加一个抽象方法便会报错
//    public void test1();

    //default方法可以任意定义
    default String test2(){
        return "123";
    }
    default String test3(){
        return "456";
    }

    //static方法也可以定义
    static void test4(){
        System.out.println("234");
    }


}
