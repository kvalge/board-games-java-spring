package com.example.boardgamesjavaspring.domain.product;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

    Product productRequestToProduct(ProductRequest request);

    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> productsToProductDtos(List<Product> products);

    ProductDto productToProductDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromProductDto(ProductDto productDto, @MappingTarget Product product);


    Product productRequestToProduct1(ProductRequest productRequest);

    ProductRequest productToProductRequest(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromProductRequest(ProductRequest productRequest, @MappingTarget Product product);

    Product updateProductAmount(Integer amount, @MappingTarget Product product);
}
