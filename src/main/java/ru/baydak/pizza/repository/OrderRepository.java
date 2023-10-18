package ru.baydak.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baydak.pizza.domain.model.Order;

/**
 * Репозиторий заказов
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
