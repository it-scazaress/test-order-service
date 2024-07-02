package com.test.orderservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.test.orderservice.application.services.OrderService;
import com.test.orderservice.application.services.PaymentService;
import com.test.orderservice.domain.model.order.Order;
import com.test.orderservice.infrastructure.repositories.order.gateway.output.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setId(1L);
        order.setAmount(100.0);
    }

    @Test
    public void testGetOrderById_Success() {
        when(orderRepositoryPort.findById(anyLong())).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);

        assertTrue(foundOrder.isPresent());
        assertEquals(order.getId(), foundOrder.get().getId());
    }

    @Test
    public void testGetOrderById_NotFound() {
        when(orderRepositoryPort.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Order> foundOrder = orderService.getOrderById(1L);

        assertFalse(foundOrder.isPresent());
    }

    @Test
    public void testListAllOrders() {
        when(orderRepositoryPort.findAll()).thenReturn(Collections.singletonList(order));

        List<Order> orders = orderService.listAllOrders();

        assertEquals(1, orders.size());
        assertEquals(order.getId(), orders.get(0).getId());
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepositoryPort).delete(anyLong());

        orderService.deleteOrder(1L);

    }

    @Test
    public void testPlaceOrder_Success() {
        when(paymentService.processPayment(any(Double.class))).thenReturn(true);
        doNothing().when(orderRepositoryPort).save(any(Order.class));

        boolean result = orderService.placeOrder(order);

        assertTrue(result);
    }

    @Test
    public void testPlaceOrder_Failure() {
        when(paymentService.processPayment(any(Double.class))).thenReturn(false);

        boolean result = orderService.placeOrder(order);

        assertFalse(result);
    }
}
