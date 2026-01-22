package com.example.Order_service.service;

import com.example.Order_service.dto.OrderDto;
import com.example.Order_service.dto.OrderEvent;
import com.example.Order_service.dto.ProductDto;
import com.example.Order_service.entity.Order;
import com.example.Order_service.exceptions.OrderNotFound;
import com.example.Order_service.feign.ProductClient;
import com.example.Order_service.kafka.KafkaProducer;
import com.example.Order_service.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final ProductClient productClient;
    private final KafkaProducer kafkaProducer;

    @Override
    public Page<Order> getAllOrders(int pageNo,
                                    int pageSize,
                                    String sortBy,
                                    String sortDir) {

        List<String>fields=
                List.of("orderId","orderName","ownerName","total");

        if (!fields.contains(sortBy)){
            sortBy="ownerName";
        }
        Sort sort=sortDir.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return orderRepo.findAll(pageable);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(()->new OrderNotFound("Order Not Found: "+id));
    }

    @Override
    public void deleteOrderById(Long id) {
    if (!orderRepo.existsById(id)){
        throw new OrderNotFound("Order Not Found:"+id);
    }
    orderRepo.deleteById(id);
    }

    @Override
    public Order addOrder(Order order) {
        ProductDto byName = productClient.getByName(order.getProductName());
        if (byName==null){
            throw new RuntimeException("Product Not Found: "+order.getProductName());
        }
        ProductDto reduceStock=productClient.reduceStock(
                                        byName.getProductName(),
                                        order.getProductQuantity());

        order.setProductName(byName.getProductName());
        order.setProductPrice(byName.getProductPrice());
        order.setTotal(byName.getProductPrice()*order.getProductQuantity());

        Order savedorder=orderRepo.save(order);
        OrderDto dto=new OrderDto(

                savedorder.getOrderId(),
                savedorder.getOwnerName(),
                savedorder.getTotal()
        );
        OrderEvent orderEvent=new OrderEvent();

        orderEvent.setMessage("Order Created..");
        orderEvent.setStatus("Notification Sent..");
        orderEvent.setOrder(dto);

        kafkaProducer.sentMessage(orderEvent);
        return savedorder;
    }
}
