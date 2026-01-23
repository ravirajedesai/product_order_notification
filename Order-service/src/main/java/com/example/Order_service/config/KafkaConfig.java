package com.example.Order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.product_email}")
    private String product_order;

    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name(product_order)
                .build();

    }
}
