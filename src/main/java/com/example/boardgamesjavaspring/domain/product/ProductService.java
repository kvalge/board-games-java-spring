package com.example.boardgamesjavaspring.domain.product;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductRepository productRepository;

    public void addNewProduct(ProductRequest request) {
        Product product = productMapper.productRequestToProduct(request);
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setPrice((product.getPrice()));
        newProduct.setAmount(product.getAmount());
        productRepository.save(newProduct);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.productsToProductDtos(products);
    }

    public ProductDto getProductByName(String name) {
        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        return productMapper.productToProductDto(byProductName);
    }

    public void updateProductByName(String name, ProductRequest request) {
        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        Product product = productMapper.updateProductFromProductRequest(request, byProductName);
        productRepository.save(product);
    }

    public void updateAmountByName(String name, int quantity) {
        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        Product product = productMapper.updateProductAmount(quantity, byProductName);
        productRepository.save(product);
    }

    public void deleteProductByName(String name) {
        productRepository.deleteByProductNameAllIgnoreCase(name);
    }
}
