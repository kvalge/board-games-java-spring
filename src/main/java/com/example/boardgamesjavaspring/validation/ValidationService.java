package com.example.boardgamesjavaspring.validation;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import com.example.boardgamesjavaspring.domain.product.ProductRequest;
import com.example.boardgamesjavaspring.domain.product_order.ProductOrder;
import com.example.boardgamesjavaspring.domain.product_order.ProductOrderRepository;
import com.example.boardgamesjavaspring.infrastructure.exception.DataNotFoundException;
import com.example.boardgamesjavaspring.infrastructure.exception.NoSuchProductExistsException;
import com.example.boardgamesjavaspring.infrastructure.exception.ProductAlreadyExistsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ValidationService {
    @Resource
    private ProductRepository productRepository;

    @Resource
    private ProductOrderRepository productOrderRepository;

    /**
     * Created to use before adding new product if the product with such a name already exists.
     */
    public String productExist(ProductRequest request) {
        Product product = productRepository.findByProductNameIgnoreCase(request.getProductName());
        if (product == null) {
            return request.getProductName() + " is added successfully";
        } else {
            String message = request.getProductName() + " already exists!";
            throw new ProductAlreadyExistsException(message);
        }
    }

    /**
     * Created to check before to return requested product if the product with such a name exists at all.
     */
    public String noSuchProductExists(String name) {
        Product product = productRepository.findByProductNameIgnoreCase(name);
        if (product != null) {
            return "Operation successfully completed!";
        } else {
            String message = name + " doesn't exists!";
            throw new NoSuchProductExistsException(message);
        }
    }

    /**
     * Created to throw exception if there are no products in database in case of request to return products
     * from database.
     */
    public String productNotFound() {
        List<Product> products = productRepository.findAll();
        if (products.size() != 0) {
            return "Operation successfully completed!";
        } else {
            String message = "No products found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Created to throw exception if it is requested to return orders on customer name that doesn't exist.
     */
    public String customerNotFound(String name) {
        List<ProductOrder> orders = productOrderRepository.findOrdersByCustomerName(name);
        if (!orders.isEmpty()) {
            return "Operation successfully completed!";
        } else {
            String message = "No orders found on that name!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Created to throw exception if there is no such an order id on requested name.
     */
    public String orderNotFound(String name, long id) {
        ProductOrder order = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        if (order != null) {
            return "Operation successfully completed!";
        } else {
            String message = "No order with id nr " + id + "!";
            throw new DataNotFoundException(message);
        }
    }
}
