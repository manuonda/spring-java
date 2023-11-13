package com.order.controller;


import com.order.service.OrderProducerServiceImpl;
import com.services.base.domain.dto.OrderDTO;
import com.services.base.domain.dto.OrderEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderProducerServiceImpl orderProducerService;


    public OrderController(OrderProducerServiceImpl orderProducerService) {
        this.orderProducerService = orderProducerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);
        orderProducerService.sendMessage(orderEvent);
        return null;
    }
}
