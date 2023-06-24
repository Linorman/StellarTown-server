package com.hllwz.stellartownserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.hllwz.stellartownserver.mapper")
public class StellarTownServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StellarTownServerApplication.class, args);
    }

}
