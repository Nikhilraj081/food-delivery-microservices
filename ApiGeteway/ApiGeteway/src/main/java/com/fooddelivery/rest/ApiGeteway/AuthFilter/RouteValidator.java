package com.fooddelivery.rest.ApiGeteway.AuthFilter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    private static final List<String> openApiEndpoints = List.of(
        "/auth-service/auth/login",
        "/auth-service/auth/register"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
  

}
