package com.jokeer.dhand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.jokeer.dhand.mapper")
public class DHandPortalApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(DHandPortalApplicaiton.class,args);
    }
}
