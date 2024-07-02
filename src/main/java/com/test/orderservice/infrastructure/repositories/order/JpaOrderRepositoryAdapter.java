package com.test.orderservice.infrastructure.repositories.order;

import com.test.orderservice.domain.model.exceptions.BusinessException;
import com.test.orderservice.domain.model.order.Order;
import com.test.orderservice.infrastructure.repositories.order.entities.OrderEntity;
import com.test.orderservice.infrastructure.repositories.order.gateway.JpaOrderRepositoryPort;
import com.test.orderservice.infrastructure.repositories.order.gateway.output.OrderRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {
    private final JpaOrderRepositoryPort jpaOrderRepositoryPort;

    public JpaOrderRepositoryAdapter(JpaOrderRepositoryPort jpaOrderRepositoryPort){
        this.jpaOrderRepositoryPort = jpaOrderRepositoryPort;

    }


    @Override
    public Optional<Order> findById(Long id) {
        Optional<Order> foundOrder = jpaOrderRepositoryPort.findById(id).map(OrderEntity::toDomainModel);
        if(foundOrder.isEmpty()){
            throw new BusinessException("Order not found");
        }
        return foundOrder;
    }

    @Override
    public void save(Order order) {
        if(order.getAmount() < 0){
            throw new BusinessException("Not valid amount");
        }
       OrderEntity orderEntity = OrderEntity.fromDomainModel(order);
       jpaOrderRepositoryPort.save(orderEntity);
    }

    @Override
    public void delete(Long id) {
          if (!jpaOrderRepositoryPort.existsById(id)) {
           throw new BusinessException("Order not found with id " + id);
        }
          jpaOrderRepositoryPort.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepositoryPort.findAll().stream().map(OrderEntity::toDomainModel).collect(Collectors.toList());
    }
}
