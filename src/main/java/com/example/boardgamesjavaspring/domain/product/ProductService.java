package com.example.boardgamesjavaspring.domain.product;

import com.example.boardgamesjavaspring.domain.product_order.ProductOrder;
import com.example.boardgamesjavaspring.domain.product_order.ProductOrderRepository;
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

    @Resource
    private ProductOrderRepository productOrderRepository;

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

    /**
     * Deletes product by name with deleting also all orders on that product beforehand.
     * Before deleting validates if requested product with such a name exists at all.
     */
    public void deleteProductByName(String name) {
        validationService.NoSuchProductExists(name);

        List<ProductOrder> orders = productOrderRepository.findOrdersByProductName(name);

        for (ProductOrder order : orders) {
            productOrderRepository.deleteById(order.getId());
        }

        productRepository.deleteByProductNameAllIgnoreCase(name);
    }
}
