package com.example.Product_service.service;

import com.example.Product_service.dto.ProductDto;
import com.example.Product_service.entity.Product;
import com.example.Product_service.exceptions.ProductNotFound;
import com.example.Product_service.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Page<Product> getAllProducts(int pageNo,
                                        int pageSize,
                                        String sortBy,
                                        String sortDir
                                        ) {
        List<String> fields=List.of("productId",
                                    "productName",
                                    "productStock",
                                    "productPrice");
        if (!fields.contains(sortBy)){
            sortBy="productName";
        }
        Sort sort=sortDir.equalsIgnoreCase("ASC")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return productRepo.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(()->new ProductNotFound("Product Not Found: "+id));
    }

    @Override
    public void deleteProductById(Long id) {
    if (!productRepo.existsById(id)){
        throw new ProductNotFound("Product Not Found: "+id);
    }
        productRepo.deleteById(id);
    }

    @Override
    public Product addProducts(Product product) {
        return productRepo.save(product);
    }

    @Override
    public ProductDto getByName(String productName) {
        Product byname= productRepo.findByProductName(productName);
        if (byname == null) {
            throw new ProductNotFound("Product Not Found: " + productName);
        }
        return new ProductDto(
                byname.getProductName(),
                byname.getProductPrice()
        );
    }

    @Override
    public ProductDto reduceProductStock(String productName,
                                             int quantity) {

        Product newproduct=productRepo.findByProductName(productName);

        if (newproduct == null) {
            throw new ProductNotFound("Product Not Found: " + productName);
        }
        if (newproduct.getProductStock()<quantity){
            throw new RuntimeException("Insufficient Product Stock"+quantity);
        }
        newproduct.setProductStock(newproduct.getProductStock()-quantity);

        Product newreq= productRepo.save(newproduct);

        return new ProductDto(
                newreq.getProductName(),
                newreq.getProductPrice()
        );
    }
}
