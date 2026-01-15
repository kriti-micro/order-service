package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PAYMENT-SERVICE",fallbackFactory = PaymentFallbackFactory.class)
public interface PaymentClient {

    @GetMapping("/payment/{id}")
    String pay(@PathVariable int id);

}
