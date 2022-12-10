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
        validationService.productNotFound();

        List<Product> products = productRepository.findAll();
        return productMapper.productsToProductDtos(products);
    }

    public ProductDto getProductByName(String name) {
        validationService.noSuchProductExists(name);

        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        return productMapper.productToProductDto(byProductName);
    }

    public void updateProductByName(String name, ProductRequest request) {
        validationService.noSuchProductExists(name);

        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        Product product = productMapper.updateProductFromProductRequest(request, byProductName);
        productRepository.save(product);
    }

    public void updateAmountByName(String name, int quantity) {
        validationService.noSuchProductExists(name);

        Product byProductName = productRepository.findByProductNameIgnoreCase(name);
        Product product = productMapper.updateProductAmount(quantity, byProductName);
        productRepository.save(product);
    }

    /**
     * Deleting product by name deletes also all orders on that product beforehand.
     * Before deleting validates if requested product with such a name exists at all.
     */
    public void deleteProductByName(String name) {
        validationService.noSuchProductExists(name);

        List<ProductOrder> orders = productOrderRepository.findOrdersByProductName(name);

        for (ProductOrder order : orders) {
            productOrderRepository.deleteById(order.getId());
        }

        productRepository.deleteByProductNameAllIgnoreCase(name);
    }

    /**
     * Deleting product by id deletes also all orders on that product beforehand.
     * Before deleting validates if requested product with such a name found by id exists at all.
     */
    public void deleteProductById(long id) {
        String productName = productRepository.findById(id).get().getProductName();
        validationService.noSuchProductExists(productName);

        List<ProductOrder> orders = productOrderRepository.findByProductId(id);

        for (ProductOrder order : orders) {
            productOrderRepository.deleteById(order.getId());
        }

        productRepository.deleteById(id);
    }
}
