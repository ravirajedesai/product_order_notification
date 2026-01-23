package com.example.Api_Gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/order-fallback")
    public Mono<ResponseEntity<String>> orderServiceFallback(){
        return Mono
                .just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Order Service is Down..Plz Try later.."));
    }
    @GetMapping("/product-fallback")
    public Mono<ResponseEntity<String>> productServiceFallback(){
        return Mono
                .just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Product Service Down.."));
    }

}
