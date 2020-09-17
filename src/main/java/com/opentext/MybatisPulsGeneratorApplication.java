package com.opentext;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = {"com.opentext.poi.mapper"})
public class MybatisPulsGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPulsGeneratorApplication.class, args);
    }


    /**
     * 将restTemplate注入spring
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        // 默认的RestTemplate，底层是走JDK的URLConnection方式。
        return new RestTemplate();
    }

}
