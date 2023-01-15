package com.demo.oauth;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class MethosOrderedByOrderIndexTest {
    @Order(1)
    @Test
    void testA(){
        System.out.println("Running test A");
    }

    @Order(2)
    @Test
    void testB(){
        System.out.println("Running test B");
    }

    @Order(3)
    @Test
    void testC(){
        System.out.println("Running test C");
    }
}
