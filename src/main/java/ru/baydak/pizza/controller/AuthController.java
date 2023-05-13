package ru.baydak.pizza.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.baydak.pizza.domain.dto.UserRegisterDTO;
import ru.baydak.pizza.service.UserService;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") UserRegisterDTO userRegisterDTO) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(
            @Valid @ModelAttribute("user")UserRegisterDTO userRegisterDTO,
            BindingResult result
    ) {
        var existing = userService.findByUsername(userRegisterDTO.getUsername());
        if (existing.isPresent()) {
            result.rejectValue("username", null, "Пользователь с таким именем уже существует");
        }

        if (result.hasErrors()) {
            return "register";
        }

       userService.register(userRegisterDTO);

        return "redirect:/login";
    }

}
