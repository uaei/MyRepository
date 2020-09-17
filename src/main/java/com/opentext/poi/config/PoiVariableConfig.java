package com.opentext.poi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 抽取可配置变量
 */

@Configuration
@EnableConfigurationProperties(PoiVariableProperties.class)
public class PoiVariableConfig {

    @Autowired
    PoiVariableProperties properties;

    public String getVariable(){
       return properties.getSimilarity();
    }
}
