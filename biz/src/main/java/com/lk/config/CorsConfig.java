package com.lk.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 跨域配置
 * @date : 2020-11-16 16:49
 */
public class CorsConfig implements WebMvcConfigurer  {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许所有的路径请求
        registry.addMapping("/**")
                //允许所有的来源
                .allowedOrigins("*")
                //允许所有类型方法
                .allowedMethods("GET","PUT","POST","DELETE","HEAT","OPTIONS")
                //配置超时
                .maxAge(3600)
                //允许所有的请求头
                .allowedHeaders("*");
    }
}