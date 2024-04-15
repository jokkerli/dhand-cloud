package com.jokeer.dhand.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jokeer.dhand.auth.mapper")
public class DHandAuthAplication {

    public static void main(String[] args) {
        SpringApplication.run(DHandAuthAplication.class,args);
    }
}
