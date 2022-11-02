package com.example.boardgamesjavaspring.domain.product;

import com.example.boardgamesjavaspring.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ValidationService validationService;

    public void addNewProduct(ProductRequest request) {
        validationService.productExist(request);

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
        validationService.NoSuchProductExists(name);

        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        return productMapper.productToProductDto(byProductName);
    }

    public void updateProductByName(String name, ProductRequest request) {
        validationService.NoSuchProductExists(name);

        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        Product product = productMapper.updateProductFromProductRequest(request, byProductName);
        productRepository.save(product);
    }

    public void updateAmountByName(String name, int quantity) {
        validationService.NoSuchProductExists(name);

        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        Product product = productMapper.updateProductAmount(quantity, byProductName);
        productRepository.save(product);
    }

    public void deleteProductByName(String name) {
        validationService.NoSuchProductExists(name);

        productRepository.deleteByProductNameAllIgnoreCase(name);
    }
}
