package com.yingejiang.sportplay.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sportplay
 * @Package com.yingejiang.sportplay.util
 * @Description: TODO
 * @date Date : 2021年06月21日 18:06
 */
@Configuration
//@ComponentScan(basePackages={"com.yingejiang.sportplay"})
public class WebConfig {
    //增加跨域权限配置
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 限制了路径和域名的访问
                registry.addMapping("/**")
                        //授权访问的前端地址
                        .allowedOrigins("http://localhost:8080")
                        //设置允许访访问 的方法
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        //设置允许的header
                        .allowedHeaders("*")
                        //是否允许证书 session相关，前端也需要加配置
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
