package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import com.example.boardgamesjavaspring.domain.product.ProductService;
import com.example.boardgamesjavaspring.validation.ValidationService;
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

    @Resource
    private ProductService productService;

    @Resource
    private ValidationService validationService;

    /**
     * Validates if product exists at all to be ordered.
     * Setting up new product order number also updates amount of products in product database.
     * Sum of total price of order is calculated on product price from product properties and
     * quantity number of order request.
     */
    public void addNewProductOrder(ProductOrderRequest request) {
        validationService.noSuchProductExists(request.getProductName());

        ProductOrder productOrder = productOrderMapper.productOrderRequestToProductOrder(request);
        Product product = productRepository.findByProductNameIgnoreCase(request.getProductName());

        ProductOrder newProduct = new ProductOrder();
        newProduct.setProduct(product);
        newProduct.setCustomer(productOrder.getCustomer());
        LocalDate orderDate = request.getOrderDate();
        newProduct.setOrderDate(orderDate);
        int quantity = request.getQuantity();
        newProduct.setQuantity(quantity);

        int newProductAmount = product.getAmount() - quantity;
        productService.updateAmountByName(product.getProductName(), newProductAmount);

        float price = product.getPrice();
        newProduct.setOrderTotalPrice(quantity * price);
        newProduct.setDeadline(orderDate.plusDays(4));
        newProduct.setStatus("In process");
        productOrderRepository.save(newProduct);
    }

    /**
     * Before returning orders by customer name checks if there are orders on that name.
     */
    public List<ProductOrderResponse> getOrdersByCustomerName(String name) {
        validationService.customerNotFound(name);

        List<ProductOrder> orders = productOrderRepository.findOrdersByCustomerName(name);
        return productOrderMapper.ordersToResponses(orders);
    }

    public List<ProductOrderCustomerResponse> getResponseByCustomerName(String name) {
        List<ProductOrder> orders = productOrderRepository.findOrdersByCustomerName(name);
        return productOrderMapper.productOrdersToProductOrderResponses(orders);
    }

    /**
     * Before updating amount of products in order it is checked if there is any order on such customer name
     * and then if there is a order with such id on such a customer name.
     * According to new order quantity, amount of products in database and the order total price are also recalculated.
     */
    public void updateAmount(String name, long id, Integer quantity) {
        validationService.customerNotFound(name);
        validationService.orderNotFound(name, id);

        ProductOrder order = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        Product product = order.getProduct();

        Integer orderAmountChange = order.getQuantity() - quantity;
        int newProductAmount = product.getAmount() + orderAmountChange;
        productService.updateAmountByName(product.getProductName(), newProductAmount);

        Float price = product.getPrice();
        Float totalPrice = price * quantity;

        ProductOrder newAmount = productOrderMapper.updateAmount(quantity, order);
        ProductOrder newTotalPrice = productOrderMapper.updateTotalPrice(totalPrice, order);

        productOrderRepository.save(newAmount);
        productOrderRepository.save(newTotalPrice);
    }

    /**
     * As creating order the first status will be 'In process' then status update gives order status value
     * 'Order delivered'.
     */
    public void updateStatus(String name, Long id) {
        ProductOrder order = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        ProductOrder status = productOrderMapper.updateStatus("Order delivered", order);
        productOrderRepository.save(status);
    }

    /**
     * Deleting order also updates amount of products in database.
     */
    public void delete(String name, long id) {
        ProductOrder order = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        Integer quantity = order.getQuantity();
        Product product = order.getProduct();
        Integer productAmount = product.getAmount();
        int newAmount = productAmount - quantity;
        productService.updateAmountByName(product.getProductName(), newAmount);

        productOrderRepository.deleteById(id);
    }
}
