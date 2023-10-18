package ru.baydak.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baydak.pizza.domain.model.User;

import java.util.Optional;


/**
 * Репозиторий Юзера, ищущий нужного юзера
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
