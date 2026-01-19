package com.example.dto;


public record ErrorResponse(
        String code,
        String message,
        String correlationId

) {
}
