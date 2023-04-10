package com.skye.srms_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.skye.srms_backend.mapper")
public class SrmsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrmsBackendApplication.class, args);
    }

}
