package com.opentext.poi.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class test {


    public static void main(String[] args) throws IOException {

        Resource resource = new ClassPathResource("/application.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);

        Object o = props.get("poi.similarity");

        System.out.println("o："+o);
        String property = props.getProperty("poi.similarity");

        System.out.println("property："+property);
    }

}
