package com.example.Product_service.controller;

import com.example.Product_service.dto.ProductDto;
import com.example.Product_service.entity.Product;
import com.example.Product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<Product>>
                getAllProducts(@RequestParam(defaultValue = "2") int pageNo,
                               @RequestParam(defaultValue = "2") int pageSize,
                               @RequestParam(defaultValue = "productName") String sortBy,
                               @RequestParam(defaultValue = "ASC") String sortDir){
        return ResponseEntity.ok(service.getAllProducts(pageNo,pageSize,sortBy,sortDir));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(service.getProductById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.addProducts(product));
    }
    @GetMapping("/name")
    public ResponseEntity<ProductDto> getByName(@RequestParam String productName){
        return ResponseEntity.ok(service.getByName(productName));
    }
    @PutMapping("/reduce")
    public ResponseEntity<ProductDto>
                            reduceStock(@RequestParam String productName,
                                        @RequestParam int quantity){
        return ResponseEntity.ok(service.reduceProductStock(productName,quantity));
    }
}
