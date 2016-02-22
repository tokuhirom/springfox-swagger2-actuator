# springfox-springboot-actuator-demo

This repository contains demo application integrating springfox and spring-boot.

## management-port-hack

Enable springfox-swagger2 on spring-boot-actuator.
I want to run it on management port for security reason.
If springfox run on management port, I can hide it by firewall very easy.

### Step1. Change port

Add `management.port=8181` property in application.properties.
It makes spring-boot-actuator run on another TCP port.

### Step2. Implement configuration classes

`@EnableSwagger2` annotation loads some classes annotated by `@Controller`.
spring-mvc loads these classes automatically. And it publishs `/v2/api-docs` and `/swagger-resources`.
I want to hide these endpoints from public port.

Then, I copied `springfox.documentation.swagger.configuration.SwaggerCommonConfiguration` to
`com.example.config.MySwaggerCommonConfiguration`.
And comment out "springfox.documentation.swagger.web" from `@ComponentScan` annotation.

I copied `springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration` to
`com.example.config.MySwagger2DocumentationConfiguration`.
And comment out `"springfox.documentation.swagger2.web",` from `@ComponentScan` annotation.

### Step3. Implement mvc endpoint
Implement `com.example.config.Swagger2ActuatorConfiguration`, `com.example.config.Swagger2Endpoint`, and
`com.example.config.Swagger2MvcEndpoint`.

`com.example.config.Swagger2MvcEndpoint` is almost copied from

### Step4. Use it

    ./gradlew bootRun

to start applicaiton.

Check http://localhost:8181/mappings to check springfox's endpoints are already hidden.

There's swagger2 json on http://localhost:8181/swagger2/.

## Future plan

### I'll send gh issue

This project uses silly *hack*. I request to restructure the springfox core.

### Service swagger ui on management port

I wonder if springfox-swagger2 services swagger-ui.
