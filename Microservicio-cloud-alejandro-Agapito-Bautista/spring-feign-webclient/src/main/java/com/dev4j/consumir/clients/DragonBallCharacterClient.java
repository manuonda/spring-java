package com.dev4j.consumir.clients;

import javax.ws.rs.GET;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dev4j.consumir.configuration.Devs4jLoadBalancerConfiguration;

@FeignClient("development")
@LoadBalancerClient(name="development", configuration = Devs4jLoadBalancerConfiguration.class)
public interface DragonBallCharacterClient {

	@RequestMapping(method = RequestMethod.GET, value="/application-name")
	public ResponseEntity<String> getApplicationName();
}
