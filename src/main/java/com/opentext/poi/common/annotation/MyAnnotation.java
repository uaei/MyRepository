package com.opentext.poi.common.annotation;

import java.lang.annotation.*;

/**
 * @Author GuYaWei
 * @created 2020/8/10 9:51
 * @description
 */

//target用来声明当前自定义的注解适合适用于什么地方，类，方法，变量，包。。。。
@Target({ElementType.METHOD, ElementType.TYPE})
//retention用来表示当前注解适用于什么环境，是源码级别还是类级别还是运行时环境，一般都是运行时环境
@Retention(RetentionPolicy.RUNTIME)
//@Inherited:表示当前注解是否能够被继承
@Inherited
public @interface MyAnnotation {

    String value();

    String name() default "vv";

    int age() default 3;
}
