package com.example;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Fallback reason: [503] during [GET] to [http://PAYMENT-SERVICE/payment/19]
 * [PaymentClient#pay(int)]: [Load balancer does not contain an instance for the service PAYMENT-SERVICE]
 * **/
@Component
public class PaymentFallbackFactory implements FallbackFactory<PaymentClient> {
    @Override
    public PaymentClient create(Throwable cause) {
        return id -> {
            System.out.println("Fallback reason: " + cause.getMessage());
            return "Payment service unavailable. Order pending.";
        };
    }
}
