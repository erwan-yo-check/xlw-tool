package org.xlw.common.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.xlw.common"})
public class ToolStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToolStarterApplication.class, args);
    }

}