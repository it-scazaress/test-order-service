package com.test.orderservice.domain.usecase.paymentservice.gateway;

public interface PaymentUseCasePort {
    boolean processPayment(double amount);
}
