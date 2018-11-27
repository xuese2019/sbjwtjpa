package com.ld.sbjwtjpa.sys;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        允许的方法地址 /api/**
        registry.addMapping("**")
//                允许的ip地址 http://192.168.1.97
                .allowedOrigins("**")
//                允许的请求方式 "get","post"
                .allowedMethods("*")
                .allowCredentials(false).maxAge(3600);
    }
}

