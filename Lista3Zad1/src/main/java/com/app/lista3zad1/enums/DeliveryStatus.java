package com.app.lista3zad1.enums;

public enum DeliveryStatus {
    CREATED("CREATED"),
    PICKED_UP("PICKED_UP"),
    DELIVERED("DELIVERED");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return getStatus();
    }
}
