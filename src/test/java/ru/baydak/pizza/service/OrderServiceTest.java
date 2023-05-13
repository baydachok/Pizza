package ru.baydak.pizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ru.baydak.pizza.domain.model.Order;
import ru.baydak.pizza.repository.OrderRepository;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testFindById() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(order.getId(), result.get().getId());
    }

    @Test
    void testSave() {
        Order order = new Order();
        order.setId(1);
        orderService.save(order);

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testDelete() {
        Order order = new Order();
        order.setId(1);
        orderService.delete(order);

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void testDeleteById() {
        orderService.deleteById(1);

        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetById() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Order result = orderService.getById(1);

        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testGetAll() {
        Order order1 = new Order();
        order1.setId(1);
        Order order2 = new Order();
        order2.setId(2);
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAll();

        assertNotNull(result);
        assertEquals(orders.size(), result.size());
        assertEquals(order1.getId(), result.get(0).getId());
        assertEquals(order2.getId(), result.get(1).getId());
    }
}
