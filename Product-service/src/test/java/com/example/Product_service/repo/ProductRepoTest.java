package com.example.Product_service.repo;

import com.example.Product_service.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ProductRepoTest {

    @Autowired
    ProductRepo productRepo;

    @Test
    void findByProductName(){

        Product product=new Product(
                null,
                "Iphone",
                50000.0,
                10
        );
        productRepo.save(product);

        Product findProduct=productRepo.findByProductName("Iphone");

        assertThat(findProduct).isNotNull();
        assertThat(findProduct.getProductName()).isEqualTo("Iphone");
        assertThat(findProduct.getProductPrice()).isEqualTo(50000.0);
        assertThat(findProduct.getProductStock()).isEqualTo(10);
    }

}