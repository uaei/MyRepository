package com.opentext.lambda.Interface;

/**
 * @Author GuYaWei
 * @created 2020/7/14 10:24
 * @description
 */
public class Demo6{
    public static void main(String[] args) {
        Cat cat= new Cat();
        System.out.println("猫的年龄是："+Cat.AGE);
        cat.eat();
        cat.run();
        cat.happy();
        Animal.sleep();
    }
}
