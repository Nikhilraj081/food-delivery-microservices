package com.fooddelivery.rest.ApiGateway.AuthFilter;

import org.springframework.stereotype.Component;

import com.fooddelivery.rest.ApiGateway.Configuration.Constants;
import com.fooddelivery.rest.ApiGateway.Exception.ApiException;
import com.fooddelivery.rest.ApiGateway.JwtSecurity.JwtHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtHelper jwtHelper;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ApiException("missing auth token in header", false);
                }

                String requestHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                logger.info(" Header :  {}", requestHeader);

                String token = null;

                if (requestHeader != null && requestHeader.startsWith(Constants.JWT_TOKEN_PREFIX)) {
                    token = requestHeader.substring(7);
                }

                if (!jwtHelper.validateToken(token)) {
                    throw new ApiException("Token is not valid", false);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }

}
