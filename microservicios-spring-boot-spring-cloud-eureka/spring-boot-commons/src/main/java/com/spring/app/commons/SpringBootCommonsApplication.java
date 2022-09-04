package com.spring.app.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
// Disabled auto configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class})
public class SpringBootCommonsApplication {


}
