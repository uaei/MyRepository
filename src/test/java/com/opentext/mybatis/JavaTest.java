package com.opentext.mybatis;


import org.junit.Test;

import java.text.MessageFormat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JavaTest {

    @Test
    public void test01() {

        String msg = "{0}{1}{2}{3}{4}{5}{6}{7}{8}";
        Object[] array = new Object[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        String value = MessageFormat.format(msg, array);
        System.out.println(value); // 输出：ABCDEFGHI
    }



}
