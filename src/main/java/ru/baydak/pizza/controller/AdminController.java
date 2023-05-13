package ru.baydak.pizza.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.baydak.pizza.domain.model.Product;
import ru.baydak.pizza.service.OrderService;
import ru.baydak.pizza.service.PizzaService;
import ru.baydak.pizza.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final PizzaService pizzaService;
    private final UserService userService;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", pizzaService.getAll());
        return "admin_products";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin_users";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "admin_orders";
    }

    @GetMapping("/orders/{id}")
    public String order(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", orderService.getById(id));
        return "admin_order";
    }

    @PostMapping("/orders/{id}/delete")
    public String deleteOrder(@PathVariable("id") int id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }
    
    @GetMapping("/products/{id}/edit")
    public String editProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", pizzaService.getById(id));
        return "admin_edit_product";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id) {
        pizzaService.deleteById(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/edit")
    public String editProductPost(Product product) {
        pizzaService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin_create_product";
    }

    @PostMapping("/products/add")
    public String addProductPost(Product product) {
        pizzaService.save(product);
        return "redirect:/admin/products";
    }

}
