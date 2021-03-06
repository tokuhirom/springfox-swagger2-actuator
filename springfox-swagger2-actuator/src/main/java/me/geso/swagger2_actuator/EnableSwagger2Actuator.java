package me.geso.swagger2_actuator;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates that Swagger2 actuator support should be enabled.
 *
 * This should be applied to a Spring java config and should have an accompanying '@Configuration' annotation.
 *
 * Loads all required beans defined in @see SpringSwaggerConfig
 *
 * @see springfox.documentation.spring.web.plugins.Docket
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({Swagger2ActuatorConfiguration.class})
public @interface EnableSwagger2Actuator {
}
