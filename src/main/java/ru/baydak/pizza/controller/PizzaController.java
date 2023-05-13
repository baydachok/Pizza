package ru.baydak.pizza.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.baydak.pizza.service.AuthService;
import ru.baydak.pizza.service.PizzaService;
import ru.baydak.pizza.service.UserService;

@Controller
@AllArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;
    private final UserService userService;
    private final AuthService authService;

    @Transactional
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("products", userService.getCart());
        model.addAttribute("totalPrice", userService.getCartTotalPrice());
        model.addAttribute("authUser", authService.getAuthUserOrNull());

        return "cart";
    }

    @GetMapping("/order/{id}")
    public String order(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", userService.getOrderById(id));
        model.addAttribute("authUser", authService.getAuthUserOrNull());
        return "order";
    }

    @PostMapping("/cart/add-product/{id}")
    public String addProductToCart(@PathVariable("id") int id) {
        if (userService.addToCartByProductId(id)) {
            return "redirect:/cart";
        } else {
            return "redirect:?error";
        }
    }

    @PostMapping("/cart/remove-product/{id}")
    public String removeProductFromCart(@PathVariable("id") int id) {
        userService.removeFromCartByProductId(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/create-order")
    public String orderCreate() {
        var order = userService.order();
        return "redirect:/order/" + order.getId();
    }
}
