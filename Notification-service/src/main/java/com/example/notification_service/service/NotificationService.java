package com.example.notification_service.service;

import com.example.notification_service.dto.OrderDto;
import com.example.notification_service.dto.OrderEvent;
import com.example.notification_service.entity.Notification;
import com.example.notification_service.repo.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;
    private final static Logger LOGGER=
            LoggerFactory.getLogger(NotificationService.class);

    @Transactional
    @KafkaListener(
            topics = "${kafka.topic.product_email}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeNotification(OrderEvent event){

        LOGGER.info("Notification Received..{}",event);

        OrderDto dto=event.getOrder();
        Notification notification=new Notification();
        notification.setOrderId(dto.getOrderId());
        notification.setOwnerName(dto.getOwnerName());
        notification.setTotal(dto.getTotal());
        notification.setMessage("Order Received Successfully..");
        notification.setStatus("Created..");

        notificationRepo.save(notification);

        LOGGER.info("Notification Saved..{}",dto.getOrderId());
    }

}
