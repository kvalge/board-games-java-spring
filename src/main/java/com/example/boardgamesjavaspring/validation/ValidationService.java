package com.example.boardgamesjavaspring.validation;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import com.example.boardgamesjavaspring.domain.product.ProductRequest;
import com.example.boardgamesjavaspring.infrastructure.exception.ProductAlreadyExistsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ValidationService {
    @Resource
    private ProductRepository productRepository;

    public String productExist(ProductRequest request) {
        Product product = productRepository.findByProductNameIgnoreCase(request.getProductName());
        if(product == null){
            return request.getProductName() + " is added successfully";
        }
        else {
            String message = request.getProductName() + " already exists!";
            throw new ProductAlreadyExistsException(message);
        }
    }
}
