package com.opentext.mybatis;

import com.opentext.MybatisPulsGeneratorApplication;
import com.opentext.poi.config.PoiVariableConfig;
import com.opentext.poi.config.PoiVariableProperties;
import com.opentext.poi.config.VariableConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPulsGeneratorApplication.class)
public class ConfigTest {


    @Test
    public void test4() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println("list：" + list);

        System.out.println("---------------------------------------------------");

        for (String sss : list) {
            if ("4".equalsIgnoreCase(sss)) {
                list.remove(sss);
            }
        }
        System.out.println("list：" + list);

        LinkedHashSet<String> strings = new LinkedHashSet<>();
        strings.add("2");
        strings.add("4");
        strings.add("5");
        strings.add("1");
        strings.add("1");
        strings.add("3");
        strings.add("5");
        strings.add("3");
        System.out.println("strings：" + strings);

        System.out.println("---------------------------------------------------");


        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("4".equalsIgnoreCase(next)) {
                iterator.remove();
                System.out.println("strings：" + next);
            }

        }
    }


    @Autowired
    PoiVariableConfig poiVariableConfig;

    @Test
    public void test5() {
        String variable = poiVariableConfig.getVariable();
        System.out.println(variable);
    }

    @Autowired
    PoiVariableProperties properties;

    @Test
    public void test6() {
        String similarity = properties.getSimilarity();
        System.out.println(similarity);
    }


    @Autowired
    VariableConfig variableConfig;

    @Test
    public void test7() {
        String similarity = variableConfig.getSimilarity();
        System.out.println(similarity);
    }

    @Value("${poi.similarity}")
    private String similarity;

    @Test
    public void test8() {
        System.out.println(similarity);
    }
}
