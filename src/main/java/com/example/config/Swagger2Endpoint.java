package com.example.config;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;

public class Swagger2Endpoint extends AbstractEndpoint
        implements Endpoint {
    public Swagger2Endpoint() {
        super("swagger2");
    }

    @Override
    public Object invoke() {
        return null;
    }
}
