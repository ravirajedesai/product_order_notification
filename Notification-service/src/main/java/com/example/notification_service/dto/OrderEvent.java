package com.example.notification_service.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private String message;
    private String status;
    private OrderDto order;
}
