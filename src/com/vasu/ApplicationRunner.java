package com.vasu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApplicationRunner {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationRunner.class);
    }
    public static void main(String[] args) {
        new ApplicationRunner().configure(new SpringApplicationBuilder(ApplicationRunner.class)).run(args);
        System.out.print("Started!!!");
    }
}