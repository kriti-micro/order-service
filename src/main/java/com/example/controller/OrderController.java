package com.example.controller;

import com.example.client.PaymentClient;
import com.example.client.PaymentFallbackFactory;
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

    public OrderController(PaymentClient paymentClient){
        this.paymentClient=paymentClient;
    }

    @GetMapping("/{id}")
    public String order(@PathVariable int id) {

        log.info("Inside Order Service:  Calling Payment Service using FeignClient." );
        //check payment status
        String str = paymentClient.pay(id);
        return "Order created with id " + id + " Payment : "+str;
    }


}
