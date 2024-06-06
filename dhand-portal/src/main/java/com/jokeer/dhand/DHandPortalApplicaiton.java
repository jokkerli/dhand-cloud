package com.jokeer.dhand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.sql.DriverManager;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.jokeer.dhand.mapper")
public class DHandPortalApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(DHandPortalApplicaiton.class,args);
//        com.mysql.jdbc.Driver
//        DriverManager.getConnection()

    }
}
