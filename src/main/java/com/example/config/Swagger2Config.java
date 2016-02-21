package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

/**
 * Re-implement Swagger2DocumentationConfiguration to exclude "springfox.documentation.swagger2.web"
 */
@Configuration
@Import({SpringfoxWebMvcConfiguration.class, MySwaggerCommonConfiguration.class})
@ComponentScan(basePackages = {
        "springfox.documentation.swagger2.readers.parameter",
//        "springfox.documentation.swagger2.web",
        "springfox.documentation.swagger2.mappers"
})
public class Swagger2Config {
    @Bean
    public JacksonModuleRegistrar swagger2Module() {
        return new Swagger2JacksonModule();
    }

    @Bean
    public Swagger2Endpoint swagger2Endpoint() {
        return new Swagger2Endpoint();
    }

    @Bean
    public Swagger2MvcEndpoint swagger2MvcEndpoint(Swagger2Endpoint swagger2Endpoint) {
        return new Swagger2MvcEndpoint(swagger2Endpoint);
    }
}
