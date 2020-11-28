package com.lk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : itrip-search启动类
 * @date : 2020-11-27 09:30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}