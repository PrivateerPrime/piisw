package com.app.lista3zad1.service;

import com.app.lista3zad1.model.Order;
import com.google.gson.Gson;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.OrderHistoryControllerApi;
import org.openapitools.client.model.Delivery;
import org.openapitools.client.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SaveToOrderHistoryService {

    private final OrderHistoryControllerApi client = new OrderHistoryControllerApi();

    public void saveToOrderHistory(Order order) throws IOException, ApiException {
        OrderDTO orderDTO = OrderDTO.fromJson(new Gson().toJson(order));
        client.createOrderHistory(orderDTO);
    }

    public void updateOrderHistory(com.app.lista3zad1.model.Delivery status, String id) throws IOException, ApiException {
        Delivery delivery = Delivery.fromJson(new Gson().toJson(status));
        client.updateDeliveryStatus(id, delivery);
    }
}
