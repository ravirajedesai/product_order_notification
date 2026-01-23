package com.example.Product_service.service;

import com.example.Product_service.dto.ProductDto;
import com.example.Product_service.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getAllProducts(
                                    int pageNo,
                                    int pageSize,
                                    String sortBy,
                                    String sortDir
    );
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product addProducts(Product product);

    ProductDto getByName(String productName);
    ProductDto reduceProductStock(String productName, int productQuantity);
}
