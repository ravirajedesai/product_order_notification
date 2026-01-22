package com.example.Order_service.kafka;

import com.example.Order_service.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final static Logger LOGGER= LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.topic.product_email}")
    private String product_order;

    private final KafkaTemplate<String , OrderEvent> kafkatemplate;

    public void sentMessage(OrderEvent event){
        LOGGER.info("Order Created..{}",event);
        Message<OrderEvent> message=
                MessageBuilder
                        .withPayload(event)
                        .setHeader(KafkaHeaders.TOPIC,product_order)
                        .build();
        kafkatemplate.send(message);

    }
}
