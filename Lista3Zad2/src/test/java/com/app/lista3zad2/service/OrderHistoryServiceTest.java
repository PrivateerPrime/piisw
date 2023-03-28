package com.app.lista3zad2.service;

import com.app.lista3zad2.enums.DeliveryStatus;
import com.app.lista3zad2.exceptions.NoSuchElement;
import com.app.lista3zad2.model.*;
import com.app.lista3zad2.repository.OrderHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderHistoryServiceTest {

    @MockBean
    private static OrderHistoryRepository orderHistoryRepository;

    private static OrderHistoryService orderHistoryService;

    private static final List<OrderHistory> resultList = List.of(
            new OrderHistory(1L, new BigDecimal(1000), "pizza,masło,jajka", "CREATED", "KURIER", "KLIENT"),
            new OrderHistory(2L, new BigDecimal(3000), "ciastka,kucyk,pomidory", "PICKED_UP", "KURIER2", "KLIENT2"),
            new OrderHistory(3L, new BigDecimal("8545.20"), "kubek,ciacho,nic", "CREATED", "KURIER3", "KLIENT3")
    );

    @BeforeEach
    public void setUp() {
        orderHistoryService = new OrderHistoryService(orderHistoryRepository);

        when(orderHistoryRepository.findAll(PageRequest.of(0,5))).thenReturn(new PageImpl<>(resultList));
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.of(resultList.get(0)));
        when(orderHistoryRepository.findById(2L)).thenReturn(Optional.of(resultList.get(1)));
        when(orderHistoryRepository.findById(3L)).thenReturn(Optional.of(resultList.get(2)));

        when(orderHistoryRepository.save(Mockito.any(OrderHistory.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }


    @Test
    void testGetAll() {
        List<OrderHistory> orders = orderHistoryService.getAll(PageRequest.of(0,5)).toList();
        assertEquals(resultList, orders);
    }

    @Test
    void testGetById() {
        Optional<OrderHistory> order = orderHistoryService.getById("2");
        assertEquals(resultList.get(1), order.get());
    }

    @Test
    void testGetByIdNoOrder() {
        Optional<OrderHistory> order = orderHistoryService.getById("4");
        assertTrue(order.isEmpty());
    }

    @Test
    void testGetByIdWrongId() {
        assertThrows(NumberFormatException.class, () -> orderHistoryService.getById("asdmosak"));
    }

    @Test
    void createOrderHistoryTest() {

        OrderDTO orderDTO = new OrderDTO(100L, List.of(new OrderItem(5L, new Product(10L, "abc", new BigDecimal(100)), 20)), new Delivery(20L, "KURIER", DeliveryStatus.PICKED_UP), "KLIENT10");
        OrderHistory orderHistory = orderHistoryService.createOrderHistory(orderDTO);

        assertEquals(2000, orderHistory.getTotalPrice().intValue());
        assertEquals("abc", orderHistory.getProductNames());
        assertEquals("PICKED_UP", orderHistory.getDeliveryStatus());
        assertEquals("KURIER", orderHistory.getCourierName());
        assertEquals("KLIENT10", orderHistory.getCustomerName());

    }

    @Test
    void updateOrderHistoryStatusTest() throws NoSuchElement {

        DeliveryStatus status = DeliveryStatus.DELIVERED;
        String id = "3";
        Optional<OrderHistory> order = orderHistoryService.getById(id);

        assertNotEquals(status.toString(), order.get().getDeliveryStatus());

        OrderHistory newOrder = orderHistoryService.updateStatus(status, id);
        assertEquals(status.toString(), newOrder.getDeliveryStatus());
    }

    @Test
    void updateOrderHistoryStatusWrongIdTest() {

        DeliveryStatus status = DeliveryStatus.DELIVERED;
        String id = "5";
        assertThrows(NoSuchElement.class, () -> orderHistoryService.updateStatus(status, id));
    }
}
