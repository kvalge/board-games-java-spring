package com.example.boardgamesjavaspring.domain.product_order;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductOrderMapper {

    ProductOrder productOrderDtoToProductOrder(ProductOrderDto productOrderDto);

    ProductOrderDto productOrderToProductOrderDto(ProductOrder productOrder);

    List<ProductOrderDto> productOrdersToProductORderDtos(List<ProductOrder> productOrders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder updateProductOrderFromProductOrderDto(ProductOrderDto productOrderDto, @MappingTarget ProductOrder productOrder);

    ProductOrder productOrderRequestToProductOrder(ProductOrderRequest productOrderRequest);

    ProductOrderRequest productOrderToProductOrderRequest(ProductOrder productOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder updateProductOrderFromProductOrderRequest(ProductOrderRequest productOrderRequest, @MappingTarget ProductOrder productOrder);
}
