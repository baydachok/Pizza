package ru.baydak.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baydak.pizza.domain.model.Product;

/**
 * Репозиторий продуктов
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
