package com.example.Order_service.feign;

import com.example.Order_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/name")
    ProductDto getByName(@RequestParam String productName);

    @PutMapping("/products/reduce")
    ProductDto reduceStock(@RequestParam String productName,
                               @RequestParam int quantity);
}
