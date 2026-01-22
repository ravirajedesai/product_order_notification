package com.example.notification_service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private String ownerName;
    private Double total;
}
