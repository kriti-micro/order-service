package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order_payment")
public class OrderPayment {
    @Id
    @GeneratedValue()
    private Long id;

    private String paymentMode; // UPI, CARD
    private String paymentStatus; // SUCCESS

    @OneToOne
    @JoinColumn(name="order_id") // Creates FK column,This is owning side of One-to-One
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
