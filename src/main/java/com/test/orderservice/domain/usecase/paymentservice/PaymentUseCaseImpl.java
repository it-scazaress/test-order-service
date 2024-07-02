package com.test.orderservice.domain.usecase.paymentservice;

import com.test.orderservice.domain.usecase.paymentservice.gateway.PaymentUseCasePort;

public class PaymentUseCaseImpl implements PaymentUseCasePort {

    double amount;

    public PaymentUseCaseImpl(double amount){
        this.amount = amount;
    }


    public boolean processPayment(double amount){
        return !(amount <= 0);
    }

}
