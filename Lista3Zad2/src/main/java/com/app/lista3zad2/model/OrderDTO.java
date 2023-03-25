package com.app.lista3zad2.model;

import java.util.List;

public record OrderDTO(Long id, List<OrderItem> items, Delivery delivery, String customerName) {
}
