package com.test.orderservice.infrastructure.controllers;

import com.test.orderservice.application.services.OrderService;
import com.test.orderservice.domain.model.exceptions.BusinessException;
import com.test.orderservice.domain.model.order.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController (OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/find-order-by-id/{id}")
    public ResponseEntity<Optional<Order>> findOrderById(@PathVariable Long id) throws BusinessException {
        Optional<Order> foundOrder = orderService.getOrderById(id);
        return new ResponseEntity<>(foundOrder, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-order/{id}")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrder(id);
    }


    @GetMapping("/get-all-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> foundOrders = orderService.listAllOrders();
        return new ResponseEntity<>(foundOrders,HttpStatus.OK);
    }

    @PostMapping("/create-order")
        public ResponseEntity<String> placeOrder(@RequestBody Order order) throws Exception {
           boolean placedOrder = orderService.placeOrder(order);

           if(!placedOrder){
               throw new Exception("Could not process this order right now");
           }
           return ResponseEntity.ok("Order placed successfully");
        }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
