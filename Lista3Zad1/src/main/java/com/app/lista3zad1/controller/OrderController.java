package com.app.lista3zad1.controller;

import com.app.lista3zad1.enums.DeliveryStatus;
import com.app.lista3zad1.exceptions.NoSuchElement;
import com.app.lista3zad1.model.Delivery;
import com.app.lista3zad1.model.Order;
import com.app.lista3zad1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>("Order created", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateStatus(@RequestBody Delivery status, @PathVariable String id) {
        try {
            DeliveryStatus statusEnum = status.getStatus();
            orderService.updateStatus(statusEnum, id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Order id is not a valid number", HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElement e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Delivery status was updated successfully", HttpStatus.OK);
    }
}
