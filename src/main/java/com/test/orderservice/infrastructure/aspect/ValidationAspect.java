package com.test.orderservice.infrastructure.aspect;

import com.test.orderservice.domain.model.exceptions.BusinessException;
import com.test.orderservice.domain.model.order.Order;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* com.test.orderservice.application.services.OrderService.placeOrder(..)) && args(order,..)")
    public void validateOrderAmount(Order order) {
        if (order.getAmount() <= 0) {
            throw new BusinessException("Amount must be greater than zero");
        }
    }

    @Before("execution(* com.test.orderservice.application.services.OrderService.getOrderById(..)) && args(id)")
    public void validateOrderId(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("Id must be greater than zero");
        }
    }

    @Before("execution(* com.test.orderservice.application.services.OrderService.deleteOrder(..)) && args(id)")
    public void validateDeleteOrderId(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("Id must be greater than zero");
        }
    }
}
