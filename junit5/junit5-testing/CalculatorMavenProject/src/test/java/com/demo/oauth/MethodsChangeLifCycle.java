package com.demo.oauth;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodsChangeLifCycle {

    StringBuilder completed = new StringBuilder("");

    @AfterEach
    void afterEach(){
        System.out.println("The state of isntance object is : "+completed);
    }

    @Order(1)
    @Test
    void testD(){
        System.out.println("Running test D");
        completed.append("1");
    }

    @Order(2)
    @Test
    void testA(){
        System.out.println("Running test A");
        completed.append("2");
    }



    @Order(3)
    @Test
    void testC(){
        System.out.println("Running test C");
        completed.append("3");
    }
    @Order(4)
    @Test
    void testB(){
        System.out.println("Running test B");
        completed.append("4");
    }
}
