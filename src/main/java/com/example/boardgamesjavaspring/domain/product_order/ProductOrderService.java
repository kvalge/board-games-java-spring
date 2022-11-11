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

    /**
     * Setting up new product order number also updates amount of products in product database.
     * Sum of total price of order is calculated on product price from product properties and
     * quantity number of order request.
     */
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

        int newProductAmount = product.getAmount() - quantity;
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

    /**
     * According to new order quantity amount of products in database and order total price are also recalculated.
     */
    public void updateAmount(String name, long id, Integer quantity) {
        ProductOrder byNameAndId = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        Product product = byNameAndId.getProduct();

        Integer orderAmountChange = byNameAndId.getQuantity() - quantity;
        int newProductAmount = product.getAmount() + orderAmountChange;
        productService.updateAmountByName(product.getProductName(), newProductAmount);

        Float price = product.getPrice();
        Float totalPrice = price * quantity;

        ProductOrder newAmount = productOrderMapper.updateAmount(quantity, byNameAndId);
        ProductOrder newTotalPrice = productOrderMapper.updateTotalPrice(totalPrice, byNameAndId);

        productOrderRepository.save(newAmount);
        productOrderRepository.save(newTotalPrice);
    }

    /**
     * As creating order the first status will be 'In process' then status update gives order status value
     * 'Order delivered'.
     */
    public void updateStatus(String name, Long id) {
        ProductOrder byCustomerAndId = productOrderRepository.findByCustomerIgnoreCaseAndId(name, id);
        ProductOrder status = productOrderMapper.updateStatus("Order delivered", byCustomerAndId);
        productOrderRepository.save(status);
    }

    /**
     * Deleting order also updates amount of products in database.
     */
    public void deleteOrderById(long id) {
        Optional<ProductOrder> order = productOrderRepository.findById(id);
        Integer quantity = order.get().getQuantity();
        Product product = order.get().getProduct();
        Integer productAmount = product.getAmount();
        int newAmount = productAmount - quantity;
        productService.updateAmountByName(product.getProductName(), newAmount);

        productOrderRepository.deleteById(id);
    }
}
