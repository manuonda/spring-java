package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {


    @Bean
    public OpenAPI customOpenApi(){
        return  new OpenAPI()
                .info(new Info()
                        .title("Demo OpenAPi")
                        .version("1.0")
                        .description("Inforamcion de description")
                        .termsOfService("Servio de terminos")
                        .license(new License().name("Prueba2").url("http://localhost:8080")));
    }

}
