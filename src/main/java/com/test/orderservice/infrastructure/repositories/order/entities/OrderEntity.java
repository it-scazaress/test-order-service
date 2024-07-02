package com.test.orderservice.infrastructure.repositories.order.entities;

import com.test.orderservice.domain.model.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;

    public OrderEntity(Long id, double amount){
        this.id = id;
        this.amount = amount;

    }

    public OrderEntity(){

    }

    public static OrderEntity fromDomainModel(Order order){
        return new OrderEntity(order.getId(), order.getAmount());
    }

    public Order toDomainModel(){
        return new Order(id, amount);
    }
}
