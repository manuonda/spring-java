package com.dev4j.consumir;

import java.util.List;

import org.aspectj.weaver.ast.Instanceof;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import com.dev4j.consumir.clients.DragonBallCharacterClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@SpringBootApplication
@EnableFeignClients
public class SpringFeignWebclientApplication implements ApplicationRunner{


	@Autowired
	private DragonBallCharacterClient dragonBallClient;
	
	private static final Logger log = LoggerFactory.getLogger(SpringFeignWebclientApplication.class);

	
	@Autowired
	private EurekaClient eurekaClient ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringFeignWebclientApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0 ; i < 10; i++) {
			 ResponseEntity<String> applicationName = dragonBallClient.getApplicationName();
			 log.info("Status {}", applicationName.getStatusCode());
			 String body = applicationName.getBody();
			 log.info("Body {} ", body);
		}
	}

	/**
	 * Implementacion de cliente eureka
//	 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		Application application = eurekaClient.getApplication("development");
//		log.info("Application {} ", application.getName());
//		List<InstanceInfo> instances = application.getInstances();
//		for(InstanceInfo instanceOf: instances) {
//			log.info("Ip address {} ", instanceOf.getIPAddr());
//		}
//	}

}
