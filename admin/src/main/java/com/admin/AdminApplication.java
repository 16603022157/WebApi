package com.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@MapperScan("com.admin.admin.dao")
@ServletComponentScan
@SpringBootApplication
/*(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})*/
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
