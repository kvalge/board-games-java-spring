package com.example.boardgamesjavaspring.domain.product_order;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductOrderMapper {


    ProductOrder productOrderDtoToProductOrder(ProductOrderDto productOrderDto);

    ProductOrderDto productOrderToProductOrderDto(ProductOrder productOrder);

    List<ProductOrderDto> productOrdersToProductOrderDtos(List<ProductOrder> productOrders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder updateProductOrderFromProductOrderDto(ProductOrderDto productOrderDto, @MappingTarget ProductOrder productOrder);

    ProductOrder productOrderRequestToProductOrder(ProductOrderRequest productOrderRequest);

    ProductOrderRequest productOrderToProductOrderRequest(ProductOrder productOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder updateProductOrderFromProductOrderRequest(ProductOrderRequest productOrderRequest, @MappingTarget ProductOrder productOrder);

    ProductOrder productOrderResponseToProductOrder(ProductOrderCustomerResponse productOrderCustomerResponse);

    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "price", source = "product.price")
    ProductOrderCustomerResponse productOrderToProductOrderResponse(ProductOrder productOrder);

    List<ProductOrderCustomerResponse> productOrdersToProductOrderResponses(List<ProductOrder> productOrders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder updateProductOrderFromProductOrderResponse(ProductOrderCustomerResponse productOrderCustomerResponse, @MappingTarget ProductOrder productOrder);

    ProductOrder productOrderResponseToProductOrder1(ProductOrderResponse productOrderResponse);

    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "price", source = "product.price")
    ProductOrderResponse orderToResponse(ProductOrder productOrder);

    List<ProductOrderResponse> ordersToResponses(List<ProductOrder> productOrders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder updateProductOrderFromProductOrderResponse1(ProductOrderResponse productOrderResponse, @MappingTarget ProductOrder productOrder);

    ProductOrder updateStatus(String status, @MappingTarget ProductOrder productOrder);
}
