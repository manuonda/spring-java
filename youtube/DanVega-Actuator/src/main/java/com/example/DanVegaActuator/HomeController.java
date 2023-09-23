package com.example.DanVegaActuator;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HomeController {

  @GetMapping("")
    public String home(){
      return "hello world";
  }
}
