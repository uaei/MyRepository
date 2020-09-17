package com.opentext.lambda.customLambda;

/**
 * @Author GuYaWei
 * @created 2020/7/14 13:19
 * @description
 */
//先定义一个函数式接口
@FunctionalInterface
public interface TestConverT<T, F> {
    F convert(T t);
}
