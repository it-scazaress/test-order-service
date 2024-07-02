package com.test.orderservice.domain.usecase.orderservice;

import com.test.orderservice.application.services.PaymentService;
import com.test.orderservice.domain.model.order.Order;
import com.test.orderservice.domain.usecase.orderservice.gateway.OrderUseCasePort;
import com.test.orderservice.infrastructure.repositories.order.gateway.output.OrderRepositoryPort;

import java.util.List;
import java.util.Optional;

public class OrderUseCaseImpl implements OrderUseCasePort {


    private final OrderRepositoryPort orderRepositoryPort;
    private final PaymentService paymentService;

    public OrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort, PaymentService paymentService){
        this.orderRepositoryPort = orderRepositoryPort;
        this.paymentService = paymentService;
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    public List<Order> listAllOrders() {
        return orderRepositoryPort.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepositoryPort.delete(id);
    }

    @Override
    public boolean placeOrder(Order order) {
        return paymentService.processPayment(order.getAmount());
    }

}
