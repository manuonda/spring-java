package com.dev4j.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@RefreshScope //permite refrescar las configuraciones que se modificacion en los properties
public class DragonBallConfiguration {
 
	@Value("${application.name}")
	private String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
}
