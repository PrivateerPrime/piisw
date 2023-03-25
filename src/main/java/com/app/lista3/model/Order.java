package com.app.lista3.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "delivery")
    private Delivery delivery;
    
    @Column(name = "customer_name")
    private String customerName;

    public Order(Long id, List<OrderItem> items, Delivery delivery, String customerName) {
        this.id = id;
        this.items = items;
        this.delivery = delivery;
        this.customerName = customerName;
    }

    public Order() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getId() {
        return id;
    }
}
