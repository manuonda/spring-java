package com.circuitbraker.ExampleCircuitBreakerServiceB;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceAController {

    @GetMapping("/b")
    public String getResponse(){
        return "Service b";
    }

}
