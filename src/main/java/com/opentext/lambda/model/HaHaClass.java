package com.opentext.lambda.model;

/**
 * @Author GuYaWei
 * @created 2020/7/2 17:42
 * @description
 */
public class HaHaClass implements HHInterface {

    private String name;

    private int age;

    @Override
    public void hehe(String name) {
        this.name = name;
    }

    @Override
    public void hehe(String name, Integer age) {
        this.name = HHInterface.HA_HA;
        this.age = HHInterface.HE_HE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "hahaClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }


    public static void main(String[] args) {

        HaHaClass haHaClass = new HaHaClass();
        haHaClass.hehe("123",456);

        System.out.println(haHaClass.toString());
    }

}


