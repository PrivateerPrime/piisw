package com.app.lista3zad2.service;

import com.app.lista3zad2.enums.DeliveryStatus;
import com.app.lista3zad2.exceptions.NoSuchElement;
import com.app.lista3zad2.model.OrderDTO;
import com.app.lista3zad2.model.OrderHistory;
import com.app.lista3zad2.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    public List<OrderHistory> getAll() {
        return orderHistoryRepository.findAll();
    }

    public Optional<OrderHistory> getById(String id) throws NumberFormatException{
        long idLong = Long.parseLong(id);
        return orderHistoryRepository.findById(idLong);
    }

    public OrderHistory createOrderHistory(OrderDTO orderDTO) {
        BigDecimal totalPrice = BigDecimal.valueOf(orderDTO.items().stream().mapToDouble(it -> it.product().price().doubleValue() * it.quantity()).sum());
        String productNames = orderDTO.items().stream().map(it -> it.product().name()).collect(Collectors.joining(","));
        String deliveryStatus = orderDTO.delivery().status().toString();
        String courierName = orderDTO.delivery().courierName();
        String customerName = orderDTO.customerName();

        OrderHistory orderHistory = new OrderHistory(orderDTO.id(), totalPrice, productNames, deliveryStatus, courierName, customerName);
        return orderHistoryRepository.save(orderHistory);
    }

    public OrderHistory updateStatus(DeliveryStatus status, String id) throws NumberFormatException, NoSuchElement {
        long idLong = Long.parseLong(id);
        Optional<OrderHistory> orderOptional = orderHistoryRepository.findById(idLong);
        if (orderOptional.isPresent()) {
            OrderHistory order = orderOptional.get();
            order.setDeliveryStatus(status.getStatus());
            return orderHistoryRepository.save(order);
        } else {
            throw new NoSuchElement("No order in database matching this id: " + id);
        }
    }


}
