package com.app.lista3zad2.controller;

import com.app.lista3zad2.enums.DeliveryStatus;
import com.app.lista3zad2.exceptions.NoSuchElement;
import com.app.lista3zad2.model.Delivery;
import com.app.lista3zad2.model.OrderDTO;
import com.app.lista3zad2.model.OrderHistory;
import com.app.lista3zad2.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/order-history")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping
    public ResponseEntity<List<OrderHistory>> getOrderHistory() {
        return new ResponseEntity<>(orderHistoryService.getAll(), HttpStatus.OK);
    }
    @SuppressWarnings("OptionalIsPresent")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderHistoryById(@PathVariable String id) {
        Optional<OrderHistory> orderHistoryOptional = orderHistoryService.getById(id);
        return orderHistoryOptional.isPresent()
                ? new ResponseEntity<>(orderHistoryOptional.get(), HttpStatus.OK)
                : new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> createOrderHistory(@RequestBody OrderDTO order) {
        orderHistoryService.createOrderHistory(order);
        return new ResponseEntity<>("Order history created", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateDeliveryStatus(@RequestBody Delivery status, @PathVariable String id) {
        try {
            DeliveryStatus statusEnum = status.status();
            orderHistoryService.updateStatus(statusEnum, id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Order id is not a valid number", HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElement e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Delivery status was updated successfully", HttpStatus.OK);
    }


}
