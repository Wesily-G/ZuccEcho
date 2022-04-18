package com.example.zuccecho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZuccEchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuccEchoApplication.class, args);
    }

}
