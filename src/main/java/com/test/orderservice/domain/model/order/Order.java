package com.test.orderservice.domain.model.order;


public class Order {

    private Long id;
    private double amount;

    public Order(Long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
