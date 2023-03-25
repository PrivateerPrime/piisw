package com.app.lista3.service;

import com.app.lista3.enums.DeliveryStatus;
import com.app.lista3.exceptions.NoSuchElement;
import com.app.lista3.model.Delivery;
import com.app.lista3.model.Order;
import com.app.lista3.model.OrderItem;
import com.app.lista3.model.Product;
import com.app.lista3.repository.DeliveryRepository;
import com.app.lista3.repository.OrderItemRepository;
import com.app.lista3.repository.OrderRepository;
import com.app.lista3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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
        for (OrderItem orderItem: order.getItems()) {
            Product product = orderItem.getProduct();
            String name = product.getName();
            BigDecimal price = product.getPrice();
            Optional<Product> optionalProduct = productRepository.findByNameAndPrice(name, price);
            if (optionalProduct.isPresent()) {
                product.setId(optionalProduct.get().getId());
                orderItem.setProduct(product);
            }
            productRepository.save(product);
            orderItemRepository.save(orderItem);
        }
        orderRepository.save(order);
    }

    public void updateStatus(DeliveryStatus status, String id) throws NumberFormatException, NoSuchElement {
        long idLong = Long.parseLong(id);
        Optional<Order> order = orderRepository.findById(idLong);
        if (order.isPresent()) {
            Delivery delivery = order.get().getDelivery();
            delivery.setStatus(status);
            deliveryRepository.save(delivery);
        } else {
            throw new NoSuchElement("No order in database matching this id: " + id);
        }
    }
}
