package com.jiangzhiyan.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author JiangZhiyan
 */
@Configuration(proxyBeanMethods = false)
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 未登录用户拦截器
     */
    @Resource(name = "unLoginInterceptor")
    private HandlerInterceptor unLoginInterceptor;

    /**
     * 判断请求为页面还是数据的拦截器
     */
    @Resource(name = "exceptionPreHandleInterceptor")
    private HandlerInterceptor exceptionPreHandleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(exceptionPreHandleInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/images/**","/js/**","/lib/**");

        registry.addInterceptor(unLoginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/images/**","/js/**","/lib/**","/","/index","/user/login");
    }
}
