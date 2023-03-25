package com.app.lista3zad2.model;

import com.app.lista3zad2.enums.DeliveryStatus;

public record Delivery(Long id, String courierName, DeliveryStatus status) {
}
