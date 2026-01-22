package com.example.Order_service.service;

import com.example.Order_service.entity.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<Order> getAllOrders(int pageNo,
                             int pageSize,
                             String sortBy,
                             String sortDir);
    Order getOrderById(Long id);
    void deleteOrderById(Long id);
    Order addOrder(Order order);
}
