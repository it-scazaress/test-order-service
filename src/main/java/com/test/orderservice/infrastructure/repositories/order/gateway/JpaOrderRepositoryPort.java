package com.test.orderservice.infrastructure.repositories.order.gateway;

import com.test.orderservice.infrastructure.repositories.order.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepositoryPort extends JpaRepository<OrderEntity,Long> {


}
