package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.Pizza;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.PizzaNotFoundException;
import webprogramming.project.online_pizza_shop.service.OrderService;
import webprogramming.project.online_pizza_shop.service.PizzaService;
import webprogramming.project.online_pizza_shop.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/menu"})
public class MenuController {


    private final PizzaService pizzaService;

    public MenuController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping // It will list all the Pizzas on the Menu page and it will show the Menu page
    public String getMenuPage(HttpServletRequest req, Model model){
        model.addAttribute("pizzas",this.pizzaService.findAll());
        model.addAttribute("bodyContent","menuPage");
        return "master-template";
    }



}
