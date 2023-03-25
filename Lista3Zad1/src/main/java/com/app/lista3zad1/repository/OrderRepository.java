package com.app.lista3zad1.repository;

import com.app.lista3zad1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
