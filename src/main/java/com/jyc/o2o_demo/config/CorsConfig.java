package com.jyc.o2o_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求配置
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域的范围
                .allowedOrigins("*")  // 允许跨域请求的域名
                .allowedMethods("*")  // 允许跨域的方法
                .allowCredentials(true).maxAge(3600);  // 允许证书，会暴露敏感信息
    }



}
