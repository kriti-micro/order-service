package com.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity // Marks this class as DB table
@Table(name="orders") //Table name
public class Order {

    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto increment
    private Long id;

    private String orderNumber;
    private Double totalamount;

    // ONE ORDER → ONE PAYMENT
    @OneToOne(mappedBy = "order",cascade=CascadeType.ALL)//inverse side, no FK here
    private OrderPayment payment;

    // ONE ORDER → MANY ITEMS
    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> items=new ArrayList<>();

    @ManyToMany
    @JoinTable(name="orders_products",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products=new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public OrderPayment getPayment() {
        return payment;
    }

    public void setPayment(OrderPayment payment) {
        this.payment = payment;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

}
