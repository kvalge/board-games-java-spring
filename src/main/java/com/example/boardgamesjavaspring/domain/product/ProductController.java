package com.example.boardgamesjavaspring.domain.product;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/new")
    @Operation(summary = "Adds new product")
    public void addNewProduct(@RequestBody ProductRequest request) {
        productService.addNewProduct(request);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Returns all products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/by/name")
    @Operation(summary = "Returns product by name")
    public ProductDto getProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @PutMapping("/update/by/name")
    @Operation(summary = "Updates product info by product name")
    public void updateProductByName(@RequestParam String name, @RequestBody ProductRequest request) {
        productService.updateProductByName(name, request);
    }

    @PutMapping("update/amount/by/name")
    @Operation(summary = "Updates product amount by name")
    public void updateAmountByName(@RequestParam String name, @RequestParam int quantity) {
        productService.updateAmountByName(name, quantity);
    }

    @DeleteMapping("/delete/by/name")
    @Operation(summary = "Deletes product by name deleting also all orders on that product beforehand")
    public void deleteProductByName(@RequestParam String name) {
        productService.deleteProductByName(name);
    }
}
