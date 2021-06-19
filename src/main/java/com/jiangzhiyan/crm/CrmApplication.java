package com.jiangzhiyan.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author JiangZhiyan
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jiangzhiyan.crm.dao")
@EnableScheduling
public class CrmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    /**
     * 设置web项目的启动入口
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CrmApplication.class);
    }
}
