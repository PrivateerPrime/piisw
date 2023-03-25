package com.app.lista3zad2.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderHistory {

    @Id
    private Long orderId;

    @Column(name = "total_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "product_names", nullable = false)
    private String productNames;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;

    @Column(name = "courier_name", nullable = false)
    private String courierName;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    public OrderHistory(Long orderId, BigDecimal totalPrice, String productNames, String deliveryStatus, String courierName, String customerName) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.productNames = productNames;
        this.deliveryStatus = deliveryStatus;
        this.courierName = courierName;
        this.customerName = customerName;
    }

    public OrderHistory() {
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
