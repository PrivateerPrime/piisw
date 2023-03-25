package com.app.lista3zad1.service;

import com.app.lista3zad1.enums.DeliveryStatus;
import com.app.lista3zad1.model.Delivery;
import com.app.lista3zad1.model.Order;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SaveToOrderHistoryService {

    public void saveToOrderHistory(Order order) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/order-history"))
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(order)))
                .header("Content-Type", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateOrderHistory(Delivery status, long id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/order-history/" + id))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(new Gson().toJson(status)))
                .header("Content-Type", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
