package com.ibeus.Comanda.Digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.ibeus.Comanda.Digital")
public class ComandaDigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComandaDigitalApplication.class, args);
    }
}