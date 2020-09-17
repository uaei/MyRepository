package com.opentext.lambda.Interface;

/**
 * @Author GuYaWei
 * @created 2020/7/14 10:23
 * @description
 */
public class Cat implements Animal{


    //接口中的默认函数default，非必须重写
//    @Override
//    public void happy() {
//
//    }

    //重写eat()抽象方法
    @Override
    public void eat() {
        System.out.println("猫在吃");
    }

    //重写默认方法run()：接口中的默认函数default，非必须重写
//    @Override
//    public void run() {
//        System.out.println("猫在跑");
//    }

}

