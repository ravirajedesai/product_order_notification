package com.example.Product_service.repo;

import com.example.Product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByProductName(String productName);
}
