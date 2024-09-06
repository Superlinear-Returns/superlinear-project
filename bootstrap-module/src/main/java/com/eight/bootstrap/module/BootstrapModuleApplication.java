package com.eight.bootstrap.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eight.user.module"})
public class BootstrapModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapModuleApplication.class, args);
    }

}
