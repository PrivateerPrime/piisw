package com.app.lista3zad1.service;

import com.app.lista3zad1.enums.DeliveryStatus;
import com.app.lista3zad1.exceptions.NoSuchElement;
import com.app.lista3zad1.model.Delivery;
import com.app.lista3zad1.model.Order;
import com.app.lista3zad1.model.OrderItem;
import com.app.lista3zad1.model.Product;
import com.app.lista3zad1.repository.DeliveryRepository;
import com.app.lista3zad1.repository.OrderItemRepository;
import com.app.lista3zad1.repository.OrderRepository;
import com.app.lista3zad1.repository.ProductRepository;
import org.openapitools.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Autowired
    private SaveToOrderHistoryService saveToOrderHistoryService;

    public void createOrder(Order order) throws IOException, ApiException {
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
        saveToOrderHistoryService.saveToOrderHistory(order);
    }

    public void updateStatus(DeliveryStatus status, String id) throws NumberFormatException, NoSuchElement, IOException, ApiException {
        long idLong = Long.parseLong(id);
        Optional<Order> order = orderRepository.findById(idLong);
        if (order.isPresent()) {
            Delivery delivery = order.get().getDelivery();
            delivery.setStatus(status);
            deliveryRepository.save(delivery);
            saveToOrderHistoryService.updateOrderHistory(delivery, id);
        } else {
            throw new NoSuchElement("No order in database matching this id: " + id);
        }
    }
}
