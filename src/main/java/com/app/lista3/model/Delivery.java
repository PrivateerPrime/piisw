package com.app.lista3.model;

import com.app.lista3.enums.DeliveryStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "courier_name", nullable = false)
    private String courierName;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(Long id, String courierName, DeliveryStatus status) {
        this.id = id;
        this.courierName = courierName;
        this.status = status;
    }

    public Delivery() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public Long getId() {
        return id;
    }

    public String getCourierName() {
        return courierName;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
