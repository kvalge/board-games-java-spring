package com.example.boardgamesjavaspring.domain.product_order;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product/order")
public class ProductOrderController {

    @Resource
    private ProductOrderService productOrderService;

    @PutMapping("/new")
    @Operation(summary = "Adds new product order")
    public void addNewProductOrder(@RequestBody ProductOrderRequest request) {
        productOrderService.addNewProductOrder(request);
    }

    @GetMapping("/get/by/customer/name")
    @Operation(summary = "Returns orders by customer name")
    public List<ProductOrderResponse> getOrdersByCustomerName(@RequestParam String name) {
        return productOrderService.getOrdersByCustomerName(name);
    }

    @GetMapping("/get/response/by/customer/name")
    @Operation(summary = "Returns orders info for customer by customer name")
    public List<ProductOrderCustomerResponse> getResponseByCustomerName(@RequestParam String name) {
        return productOrderService.getResponseByCustomerName(name);
    }

    @DeleteMapping("/delete/by/id")
    @Operation(summary = "Deletes order by id")
    public void deleteOrderById(@RequestParam long id) {
        productOrderService.deleteOrderById(id);
    }
}
