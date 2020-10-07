package com.ouyangwei.multimodule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulLoginApplication {
    public static void main(String[] args){
        SpringApplication.run(RestfulLoginApplication.class,args);
    }
}
