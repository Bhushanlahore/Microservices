package com.easybytes.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class ResponseTraceFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTraceFilter.class);
	
	@Autowired
	FilterUtility filterUtility;
	
	@Bean
	 GlobalFilter postGlobalFilter() {
		
		return (exchange, chain)->{
		return	chain.filter(exchange).then(Mono.fromRunnable(()->{
				
				HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
				String correlationId = filterUtility.getCorelationId(requestHeaders);
				
				LOGGER.debug("updated correlation id to the outbound header{}", correlationId);
				
				exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID, correlationId);
				
			}));
		};
	}
	

}
