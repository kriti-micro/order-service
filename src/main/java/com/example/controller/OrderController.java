package com.example.controller;

import com.example.client.PaymentClient;
import com.example.client.PaymentFallbackFactory;
import com.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    public PaymentClient paymentClient;
    private final OrderService orderService;

    public OrderController(PaymentClient paymentClient, OrderService orderService){
        this.paymentClient=paymentClient;
        this.orderService=orderService;
    }

    @GetMapping("/{id}")
    public String order(@PathVariable int id) {

        log.info("Inside Order Service:  Calling Payment Service using FeignClient." );
        //check payment status
        String str = paymentClient.pay(id);
        return "Order created with id " + id + " Payment : "+str;
    }

    //Exception check
    @GetMapping("/exception/{id}")
    public String getOrderById(@PathVariable Long id) {
        String str = orderService.getOrderById(id);
        return str;
    }


}
