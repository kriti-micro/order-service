package com.example.dto;

public record OrderPaymentResponse (
        Long id,
        String paymentMode,
        String paymentStatus
){
}
