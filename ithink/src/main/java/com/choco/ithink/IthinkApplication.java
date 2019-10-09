package com.choco.ithink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.choco.ithink.DAO.mapper")
@SpringBootApplication
public class IthinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(IthinkApplication.class, args);
    }

}
