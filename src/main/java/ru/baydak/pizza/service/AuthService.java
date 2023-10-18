package ru.baydak.pizza.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.baydak.pizza.domain.model.User;
import ru.baydak.pizza.repository.UserRepository;
import ru.baydak.pizza.security.SecurityUser;

import java.util.Optional;

/**
 * Сервис отвечающий за авторизацию
 */
@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    /**
     * Получение авторизованного пользователя
     * @return
     */
    public Optional<User> getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.of(((SecurityUser) authentication.getPrincipal()).getUser());
    }

    /**
     * Получение авторизованного пользователя
     * @return
     */
    public User getAuthUserOrNull() {
        var authUser = getAuthUser().orElse(null);
        if (authUser == null) {
            return null;
        } else {
            return userRepository.findById(authUser.getId()).orElse(null);
        }
    }
}