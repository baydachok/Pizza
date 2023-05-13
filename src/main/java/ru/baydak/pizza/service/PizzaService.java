package ru.baydak.pizza.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baydak.pizza.domain.model.Product;
import ru.baydak.pizza.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PizzaService {
    private final ProductRepository productRepository;

    /**
     * Получение всех продуктов
     * @return
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Получение конкретного продукта
     * @return
     */
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    /**
     * Получение конкретного продукта
     * @return
     */
    public Product getById(int id) {
        return findById(id).orElseThrow();
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }
}