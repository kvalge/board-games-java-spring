package com.example.boardgamesjavaspring.domain.product_order;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductOrderMapper {

    ProductOrder productOrderRequestToProductOrder(ProductOrderRequest productOrderRequest);

    ProductOrder updateAmount(Integer quantity, @MappingTarget ProductOrder productOrder);

    ProductOrder updateTotalPrice(Float totalPrice, @MappingTarget ProductOrder productOrder);

    ProductOrder updateStatus(String status, @MappingTarget ProductOrder productOrder);

    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "price", source = "product.price")
    ProductOrderCustomerResponse productOrderToProductOrderResponse(ProductOrder productOrder);

    List<ProductOrderCustomerResponse> productOrdersToProductOrderResponses(List<ProductOrder> productOrders);

    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "price", source = "product.price")
    ProductOrderResponse orderToResponse(ProductOrder productOrder);

    List<ProductOrderResponse> ordersToResponses(List<ProductOrder> productOrders);
}
