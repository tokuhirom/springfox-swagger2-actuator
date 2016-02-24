package me.geso.swagger2_actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

@Import({SpringfoxWebMvcConfiguration.class})
@ComponentScan(basePackages = {
        "springfox.documentation.swagger.schema",
        "springfox.documentation.swagger.readers",
        "springfox.documentation.swagger2.readers.parameter",
        "springfox.documentation.swagger2.mappers"
})
@Configuration
public class Swagger2ActuatorConfiguration {
    @Bean
    public Swagger2Endpoint swagger2Endpoint() {
        return new Swagger2Endpoint();
    }

    @Bean
    public JacksonModuleRegistrar swagger2Module() {
        return new Swagger2JacksonModule();
    }

    @Bean
    public Swagger2MvcEndpoint swagger2MvcEndpoint(Swagger2Endpoint swagger2Endpoint) {
        return new Swagger2MvcEndpoint(swagger2Endpoint);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertySourcesPlaceholderConfigurer;
    }
}
