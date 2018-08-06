package com.mooracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the Application starting point build spesifically to boot Spring framework
 *
 * Basically it just fired up the Spring application that will build all classes instatiate entities as POJO
 * */

@SpringBootApplication // <- marked as Spring Boot Application to starting point of apps
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
