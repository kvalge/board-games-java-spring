package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductOrderService {

    @Resource
    private ProductOrderMapper productOrderMapper;

    @Resource
    private ProductOrderRepository productOrderRepository;

    @Resource
    private ProductRepository productRepository;

    public void addNewProductOrder(ProductOrderRequest request) {
        ProductOrder productOrder = productOrderMapper.productOrderRequestToProductOrder(request);
        Product product = productRepository.findByProductNameIgnoreCase(request.getProductName());
        ProductOrder newProduct = new ProductOrder();
        newProduct.setProduct(product);
        newProduct.setCustomer(productOrder.getCustomer());
        LocalDate orderDate = request.getOrderDate();
        newProduct.setOrderDate(orderDate);
        int quantity = request.getQuantity();
        newProduct.setQuantity(quantity);
        float price = product.getPrice();
        newProduct.setOrderTotalPrice(quantity * price);
        newProduct.setDeadline(orderDate.plusDays(4));
        newProduct.setStatus("In process");
        productOrderRepository.save(newProduct);
    }

    public List<ProductOrderResponse> getOrdersByCustomerName(String name) {
        List<ProductOrder> ordersByCustomerName = productOrderRepository.findOrdersByCustomerName(name);
        return productOrderMapper.ordersToResponses(ordersByCustomerName);
    }

    public List<ProductOrderCustomerResponse> getResponseByCustomerName(String name) {
        List<ProductOrder> orders = productOrderRepository.findOrdersByCustomerName(name);
        return productOrderMapper.productOrdersToProductOrderResponses(orders);
    }
}
