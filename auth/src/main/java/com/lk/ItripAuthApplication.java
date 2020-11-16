package com.lk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.lk.mapper")
public class ItripAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItripAuthApplication.class, args);
    }

}
