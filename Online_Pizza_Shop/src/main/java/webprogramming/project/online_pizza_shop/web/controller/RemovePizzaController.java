package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.Pizza;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.PizzaNotFoundException;
import webprogramming.project.online_pizza_shop.service.OrderService;
import webprogramming.project.online_pizza_shop.service.PizzaService;
import webprogramming.project.online_pizza_shop.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/remove"})
public class RemovePizzaController {

    private final UserService userService;
    private final OrderService orderService;
    private final PizzaService pizzaService;


    public RemovePizzaController(UserService userService, OrderService orderService, PizzaService pizzaService) {
        this.userService = userService;
        this.orderService = orderService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public String getRemovePizzaPage(HttpServletRequest req,
                                     Model model){
        model.addAttribute("pizzas", this.orderService.getActiveOrder(req.getRemoteUser()).getPizza());
        model.addAttribute("bodyContent", "removePizzaFromOrderPage");
        return "master-template";
    }

    @PostMapping("/{id}") //It is used to remove pizzas from order
    public String removePizza(@PathVariable Long id, HttpServletRequest req, Model model){


        String username=req.getRemoteUser();//req.getRemoteUser() it will get the users username

        //loadUserByUsername(req.getRemoteUser()) it will get the User from username
        User user = (User) this.userService.loadUserByUsername(username);

//        Order order =  this.orderService.getActiveOrder(user.getUsername());

        if(this.pizzaService.findById(id).isPresent()){
            Pizza pizza = this.pizzaService.findById(id).orElseThrow(PizzaNotFoundException::new);
            this.orderService.getActiveOrder(user.getUsername()).getPizza().remove(pizza);
            this.orderService.save(this.orderService.getActiveOrder(user.getUsername()));
        }

        model.addAttribute("pizzas", this.orderService.getActiveOrder(req.getRemoteUser()).getPizza());
        return "redirect:/remove";
    }
}
