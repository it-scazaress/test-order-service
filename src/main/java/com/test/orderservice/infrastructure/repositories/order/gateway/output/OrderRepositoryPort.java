package com.test.orderservice.infrastructure.repositories.order.gateway.output;

import com.test.orderservice.domain.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Optional<Order> findById(Long id);
    void save(Order order);
    void delete(Long id);
    List<Order> findAll();



}
