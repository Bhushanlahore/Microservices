package com.easybytes.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter{

	 private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);
	 
	 @Autowired 
	 FilterUtility filterUtility;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
		
		if(isCorrelationIdPresent(requestHeaders)) {
			
			logger.debug("eazyBank-correlation-id found in RequestTraceFilter: {}",
					filterUtility.getCorelationId(requestHeaders));
		}else {
			
			String correlationId = generateCorrealationId();
			
			exchange = filterUtility.setCorrelationId(exchange, correlationId);
			
			logger.debug("eazyBank-correlation-id generated in RequestTraceFilter: {}",
					correlationId);
		}
		
		// TODO Auto-generated method stub
		return chain.filter(exchange);
	}
	
	
	public boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
		
		if(filterUtility.getCorelationId(requestHeaders) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	private String generateCorrealationId() {
		
		return java.util.UUID.randomUUID().toString();
	}
}