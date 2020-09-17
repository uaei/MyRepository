package com.opentext.poi.common.annotation;

import java.lang.reflect.Field;

/**
 * @Author GuYaWei
 * @created 2020/8/10 9:50
 * @description
 */

@MyAnnotation(value = "hahh",name = "skdjif")
public class AnnotationDemo {

    public static void main(String[] args) {
        Class<AnnotationDemo> clazz = AnnotationDemo.class;

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation annotation = declaredField.getAnnotation(MyAnnotation.class);
                System.out.println(annotation.age()+"--------"+annotation.value());
            }
        }

    }


}
