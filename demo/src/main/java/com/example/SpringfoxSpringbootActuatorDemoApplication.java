package com.example;

import com.google.common.collect.Sets;
import me.geso.swagger2_actuator.EnableSwagger2Actuator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableSwagger2Actuator
public class SpringfoxSpringbootActuatorDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringfoxSpringbootActuatorDemoApplication.class, args);
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build()
                .consumes(Sets.newHashSet("application/json"))
                .produces(Sets.newHashSet("application/json"))
                .apiInfo(new ApiInfoBuilder()
                        .title("My awesome API")
                        .license("Commerical")
                        .build())
                ;
    }
}
