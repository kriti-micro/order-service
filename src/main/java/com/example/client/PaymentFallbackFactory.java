package com.example.client;

import com.example.controller.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Fallback reason: [503] during [GET] to [http://PAYMENT-SERVICE/payment/19]
 * [PaymentClient#pay(int)]: [Load balancer does not contain an instance for the service PAYMENT-SERVICE]
 * **/
@Component
public class PaymentFallbackFactory implements FallbackFactory<PaymentClient> {

    private static final Logger log = LoggerFactory.getLogger(PaymentFallbackFactory.class);

    @Override
    public PaymentClient create(Throwable cause) {
        return id -> {
            log.info("Fallback reason: {} " , cause.getMessage());
            return "Payment service unavailable. Order pending.";
        };
    }
}
