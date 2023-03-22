package com.app.lista3.controller;

import com.app.lista3.model.Order;
import com.app.lista3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
