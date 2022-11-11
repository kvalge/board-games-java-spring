package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import com.example.boardgamesjavaspring.domain.product.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {

    @Resource
    private ProductOrderMapper productOrderMapper;

    @Resource
    private ProductOrderRepository productOrderRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ProductService productService;

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


        Integer newProductAmount = product.getAmount() - quantity;
        productService.updateAmountByName(product.getProductName(), newProductAmount);

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

    public void updateAmount(String name, long id, Integer quantity) {
        ProductOrder byNameAndId = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        Product product = byNameAndId.getProduct();

        Integer orderAmountChange = byNameAndId.getQuantity() - quantity;
        Integer newProductAmount = product.getAmount() + orderAmountChange;
        productService.updateAmountByName(product.getProductName(), newProductAmount);

        Float price = product.getPrice();
        Float totalPrice = price * quantity;

        ProductOrder newAmount = productOrderMapper.updateAmount(quantity, byNameAndId);
        ProductOrder newTotalPrice = productOrderMapper.updateTotalPrice(totalPrice, byNameAndId);

        productOrderRepository.save(newAmount);
        productOrderRepository.save(newTotalPrice);
    }

    public void updateStatus(String name, Long id) {
        ProductOrder byCustomerAndId = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        ProductOrder status = productOrderMapper.updateStatus("Order delivered", byCustomerAndId);
        productOrderRepository.save(status);
    }

    public void deleteOrderById(long id) {
        Optional<ProductOrder> order = productOrderRepository.findById(id);
        Integer quantity = order.get().getQuantity();
        Product product = order.get().getProduct();
        Integer productAmount = product.getAmount();
        Integer newAmount = productAmount - quantity;
        productService.updateAmountByName(product.getProductName(), newAmount);

        productOrderRepository.deleteById(id);
    }
}
