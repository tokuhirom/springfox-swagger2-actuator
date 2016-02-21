package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.swagger.configuration.SwaggerCommonConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

@SpringBootApplication
public class SpringfoxSpringbootActuatorDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringfoxSpringbootActuatorDemoApplication.class, args);
    }
}
