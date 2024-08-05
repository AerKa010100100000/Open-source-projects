package com.cx330.supplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cx330.supplier.mapper")
public class SupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class, args);
    }

}
