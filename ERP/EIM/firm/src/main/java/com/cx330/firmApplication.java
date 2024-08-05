package com.cx330;

import org.jetbrains.annotations.NotNull;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.cx330.mapper")
public class firmApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(firmApplication.class);
        springApplication.run(args);
        Logger logger = LoggerFactory.getLogger(firmApplication.class);
    }

    //配置RestTemplate
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
