package com.app.lista3.service;

import com.app.lista3.model.Order;
import com.app.lista3.repository.DeliveryRepository;
import com.app.lista3.repository.OrderItemRepository;
import com.app.lista3.repository.OrderRepository;
import com.app.lista3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createOrder(Order order) {
        deliveryRepository.save(order.getDelivery());
        orderRepository.save(order);
    }
}
