package com.cx330.department;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.cx330.department.mapper")
public class DepartmentApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DepartmentApplication.class);
        springApplication.run(args);
        springApplication.setBannerMode(Banner.Mode.OFF);
    }

    //配置RestTemplate
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
