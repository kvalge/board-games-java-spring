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

    @GetMapping("/get/all")
    @Operation(summary = "Returns orders by customer name")
    public List<ProductOrderResponse> getOrdersByCustomerName(@RequestParam String name) {
        return productOrderService.getOrdersByCustomerName(name);
    }

    @GetMapping("/get/response")
    @Operation(summary = "Returns orders info for customer by customer name")
    public List<ProductOrderCustomerResponse> getResponseByCustomerName(@RequestParam String name) {
        return productOrderService.getResponseByCustomerName(name);
    }

    @PutMapping("/update/amount")
    @Operation(summary = "Updates amount by customer name and order id")
    public void updateAmount(@RequestParam String name, @RequestParam long id, @RequestParam Integer quantity) {
        productOrderService.updateAmount(name, id, quantity);
    }

    @PutMapping("/update/status")
    @Operation(summary = "Updates order status by customer name and order id")
    public void updateStatus(@RequestParam String name, @RequestParam Long id) {
        productOrderService.updateStatus(name, id);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deletes order by customer name and order id")
    public void delete(@RequestParam String name, @RequestParam long id) {
        productOrderService.delete(name, id);
    }
}
