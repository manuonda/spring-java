package com.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.gateway.models.TokenDTO;

import reactor.core.publisher.Mono;
/**
 * Component de filter de Authorization
 * @author  dgarcia
 * @version 1.0 
 *
 */

@Component
public class AuthFilter  implements GatewayFilter{

	@Autowired
	private WebClient.Builder webClient;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		  ServerHttpRequest request = exchange.getRequest();
		  if( !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			  return this.onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
		  } 
		  String authToken = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		  String[] chucks = authToken.split(" ");
		  if (chucks.length != 2 || !chucks[0].equals("Bearer")) {
			 return  onError(exchange, "Bad Request", HttpStatus.BAD_REQUEST);
		  }
		 return webClient.build().post().uri("http://spring-authentication/validate?token=" + chucks[1])
				 .retrieve().bodyToMono(TokenDTO.class).map( t -> {
					 return exchange;
				 }).flatMap(chain::filter);
	}
	
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus)  {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
	
    }
}
