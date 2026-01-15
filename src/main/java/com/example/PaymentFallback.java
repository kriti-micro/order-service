package com.example;

import org.springframework.stereotype.Component;

/**
 * It does not work as it fails at load balancing level [internal server]
 * so we add circuit breaker and add factory code
 * **/
@Component
public class PaymentFallback implements PaymentClient{

    @Override
    public String pay(int id) {
        return "Payment Service is down";
    }
}
