package com.easybytes.apigateway;

import java.io.ObjectInputFilter.Config;
import java.time.LocalDateTime;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
		
	}
	
	@Bean
	RouteLocator easyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes()
				.route(p-> p
						.path("/eazybank/accounts/**")
						.filters(f->f.rewritePath("/eazybank/accounts/(?<segment>.*)", "/${segment}")
						.addResponseHeader("X-Response_Time", LocalDateTime.now().toString())
						.circuitBreaker(config -> config.setName("accountCircuitBreaker")
						.setFallbackUri("forward:/contact-support")))
						.uri("lb://ACCOUNTS"))
				.route(p->p
						.path("/eazybank/cards/**")
						.filters(f->f.rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}")
						.addResponseHeader("X-Response_Time", LocalDateTime.now().toString())
						.circuitBreaker(config -> config.setName("accountCircuitBreaker")
						.setFallbackUri("forward:/contact-support")))
						.uri("lb://CARDS"))
				.route(p->p
						.path("/eazybank/loans/**")
						.filters(f->f.rewritePath("/eazybank/loans/(?<segment>.*)", "/${segment}")
						.addResponseHeader("X-Response_Time", LocalDateTime.now().toString())
						.circuitBreaker(config -> config.setName("accountCircuitBreaker")
						.setFallbackUri("forward:/contact-support")))
						.uri("lb://LOANS")).build();	
	}
	
	

}
