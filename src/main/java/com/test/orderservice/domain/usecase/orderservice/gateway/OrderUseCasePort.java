package com.test.orderservice.domain.usecase.orderservice.gateway;

import com.test.orderservice.domain.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderUseCasePort {
    Optional<Order> getOrderById(Long id);
    List<Order>listAllOrders();
    void deleteOrder(Long id);
    boolean placeOrder(Order order);
}
