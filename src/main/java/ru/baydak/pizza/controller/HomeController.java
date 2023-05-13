package ru.baydak.pizza.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.baydak.pizza.service.AuthService;
import ru.baydak.pizza.service.PizzaService;

@Controller
@AllArgsConstructor
public class HomeController {
    private final PizzaService pizzaService;
    private final AuthService authService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("products", pizzaService.getAllProducts());
        model.addAttribute("authUser", authService.getAuthUserOrNull());

        return "index";
    }
}
