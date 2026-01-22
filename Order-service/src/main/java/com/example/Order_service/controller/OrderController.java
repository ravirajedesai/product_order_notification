package com.example.Order_service.controller;

import com.example.Order_service.entity.Order;
import com.example.Order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<Page<Order>>
    getAllOrders(@RequestParam(defaultValue = "2") int pageNo,
                 @RequestParam(defaultValue = "2") int pageSize,
                 @RequestParam(defaultValue = "ownerName") String sortBy,
                 @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(service.getAllOrders(pageNo,pageSize,sortBy,sortDir));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(service.getOrderById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        service.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return ResponseEntity.ok(service.addOrder(order));
    }
}
