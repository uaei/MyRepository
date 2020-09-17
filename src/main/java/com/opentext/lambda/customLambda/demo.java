package com.opentext.lambda.customLambda;

/**
 * @Author GuYaWei
 * @created 2020/7/14 11:16
 * @description
 */
public class demo {

    public static void main(String[] args) {

        MyLamda myLamda = i -> System.out.println("???：" + i);
        myLamda.test("bbb");

        System.out.println(myLamda.test2());
        System.out.println(myLamda.test3());

        TestConverT<String,Integer> converT = Integer::valueOf;
        Integer convert = converT.convert("111");
        System.out.println(convert);

    }

}
