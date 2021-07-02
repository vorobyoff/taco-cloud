package com.tacos.tacocloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {

    public static void main(String... args) {
        run(TacoCloudApplication.class, args);
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}