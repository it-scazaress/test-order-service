package com.test.orderservice.infrastructure.config;

import com.test.orderservice.application.services.OrderService;
import com.test.orderservice.application.services.PaymentService;
import com.test.orderservice.domain.usecase.orderservice.OrderUseCaseImpl;
import com.test.orderservice.domain.usecase.paymentservice.PaymentUseCaseImpl;
import com.test.orderservice.domain.usecase.paymentservice.gateway.PaymentUseCasePort;
import com.test.orderservice.infrastructure.repositories.order.JpaOrderRepositoryAdapter;
import com.test.orderservice.infrastructure.repositories.order.gateway.JpaOrderRepositoryPort;
import com.test.orderservice.infrastructure.repositories.order.gateway.output.OrderRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {



    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, PaymentService paymentService) {
        return new OrderService(orderRepositoryPort, paymentService);
    }

    @Bean
    public OrderUseCaseImpl orderUseCase(OrderRepositoryPort orderRepositoryPort,PaymentService paymentService) {
        return new OrderUseCaseImpl(orderRepositoryPort, paymentService);
    }

   @Bean
    public PaymentService paymentService(PaymentUseCaseImpl paymentUseCaseImpl) {
        return new PaymentService(paymentUseCaseImpl);
    }

     @Bean
    public PaymentUseCaseImpl paymentUseCaseImpl() {
        return new PaymentUseCaseImpl(0);
    }

    @Bean
    public OrderRepositoryPort orderRepositoryPort(JpaOrderRepositoryAdapter jpaOrderRepositoryAdapter) {
        return jpaOrderRepositoryAdapter;
    }

    @Bean
    public JpaOrderRepositoryAdapter jpaOrderRepositoryAdapter(JpaOrderRepositoryPort jpaOrderRepositoryPort) {
        return new JpaOrderRepositoryAdapter(jpaOrderRepositoryPort);
    }

}
