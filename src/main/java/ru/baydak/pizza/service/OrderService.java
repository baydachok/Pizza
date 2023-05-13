package ru.baydak.pizza.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baydak.pizza.domain.model.Order;
import ru.baydak.pizza.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Order getById(Integer id) {
        return findById(id).orElse(null);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
