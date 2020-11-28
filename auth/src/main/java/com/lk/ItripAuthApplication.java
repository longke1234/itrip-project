package com.lk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(value = "com.lk.mapper")
@EnableDiscoveryClient
//@ComponentScan(basePackages = {"com.lk.itripauth","com.lk.handler"})
@SpringBootApplication()
public class ItripAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItripAuthApplication.class, args);
    }

}
