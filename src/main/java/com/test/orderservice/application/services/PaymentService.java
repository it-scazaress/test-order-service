package com.test.orderservice.application.services;

import com.test.orderservice.domain.usecase.paymentservice.PaymentUseCaseImpl;
import com.test.orderservice.domain.usecase.paymentservice.gateway.PaymentUseCasePort;

public class PaymentService implements PaymentUseCasePort{
    private final PaymentUseCaseImpl paymentUseCaseImpl;

    public PaymentService(PaymentUseCaseImpl paymentUseCaseImpl) {
        this.paymentUseCaseImpl = paymentUseCaseImpl;
    }

    @Override
    public boolean processPayment(double amount) {
        return paymentUseCaseImpl.processPayment(amount);
    }
}
