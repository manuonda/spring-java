package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



// Exception not valid argument exception : https://www.youtube.com/watch?v=gPnd-hzM_6A
// OpenApi : https://www.baeldung.com/spring-rest-openapi-documentation

//  junit: https://www.javaguides.net/2022/03/spring-boot-unit-testing-service-layer.html
 // junit 2:  https://howtodoinjava.com/spring-boot/spring-boot-test-controller-service-dao/
@SpringBootApplication
public class ArticleJunit5Application {

	public static void main(String[] args) {
		SpringApplication.run(ArticleJunit5Application.class, args);
	}

}


// TODO :
// 1 - Completar test
// 2 - Security habiltiar con jwt
// 3 - Aplicar con Roles y Usuarios
// 4 - Aplicar kafka guardar en una base de datos cuando se crea
// 5 _ Usar Prometheus y pattern observability