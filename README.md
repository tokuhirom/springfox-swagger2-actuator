# springfox-swagger2-actuator

This library provides a way to publish springfox-swagger2 on spring boot actuator.

## Installation

TBD

## Why?

I want to serve it on management port for security reason.
If springfox run on management port, I can hide it by firewall very easy.

Sometime, I want to share the API information with our frontend
 engineer team, but I don't hope to publish on www.

I asked to merge this library to springfox core repository, but the maintainer says

> Honestly, I don't have the bandwidth to maintain this on my own, but I'd be happy to use your help if you're willing to shepherd this and handle any support/issues that might come up. At the very least, i'll definitely link to your repository and direct folks your way!

see https://github.com/springfox/springfox/issues/1184

## Usage

Usage is really simple. You need to use `@EnableSwagger2InActuator` annotation instead of `@EnableSwagger2` annotation.

```
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
```

For more details, please look springfox's document: https://github.com/springfox/springfox

## Configuration Properties

There's some configuration properties.

### springfox_swagger2_actuator.access_control_allow_origin

Set `Access-Control-Allow-Origin` header value to allow CORS.
Normally, swagger-ui requires CORS.

When the value is null, springfox-swagger2-actuator won't send `Access-Control-Allow-Origin` header.

Default: null

### springfox_swagger2_actuator.access_control_allow_headers

Set `Access-Control-Allow-Headers` header value for CORS.

When the value is null, springfox-swagger2-actuator won't send `Access-Control-Allow-Headers` header.

Default: null

## FAQ

### How do I change the spring boot actuator port?

Add `management.port=8181` property in application.properties.
It makes spring-boot-actuator run on another TCP port.
