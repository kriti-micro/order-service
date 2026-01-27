package com.example.dto;

public record OrderItemResponse(
        Long id,
        String productName,
        Integer qty,
        Double price
){
}
