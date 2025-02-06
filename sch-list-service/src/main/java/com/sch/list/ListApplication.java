package com.sch.list;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.sch.list.mapper"})
public class ListApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ListApplication.class, args);
    }
}
