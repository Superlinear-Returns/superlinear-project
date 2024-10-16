package com.eight.bootstrap.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eight.bootstrap.module", "com.eight.user.module",
        "com.eight.order.module", "com.eight.product.module", "com.eight.common.module"})
public class BootstrapModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapModuleApplication.class, args);
    }

}
