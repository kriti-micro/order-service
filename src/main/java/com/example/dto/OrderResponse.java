package com.example.dto;

import com.example.entity.OrderItem;
import com.example.entity.OrderPayment;

import java.util.List;

public record OrderResponse (
        Long id,
        String orderNumber,
        Double totalAmount,
        OrderPaymentResponse payment,
        List<OrderItemResponse> items
){
}
