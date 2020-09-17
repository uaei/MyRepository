package com.opentext.poi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 参数注解
 *
 * @author xhuang
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Repeatable(Param.List.class)
public @interface Param {

    /**
     * 参数名称，为空为属性名称
     *
     * @return
     */
    String name() default "";

    /**
     * 是否必须参数，默认为 true
     *
     * @return
     */
    boolean required() default true;

    /**
     * 参数描述
     *
     * @return
     */
    String description() default "";

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Param[] value();
    }
}
