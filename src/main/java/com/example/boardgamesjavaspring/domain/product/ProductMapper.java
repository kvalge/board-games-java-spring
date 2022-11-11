package com.example.boardgamesjavaspring.domain.product;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

    Product productRequestToProduct(ProductRequest request);

    Product updateProductAmount(Integer amount, @MappingTarget Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromProductRequest(ProductRequest productRequest, @MappingTarget Product product);

    ProductDto productToProductDto(Product product);

    List<ProductDto> productsToProductDtos(List<Product> products);
}
