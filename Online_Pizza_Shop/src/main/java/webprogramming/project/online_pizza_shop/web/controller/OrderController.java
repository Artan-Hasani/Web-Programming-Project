package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webprogramming.project.online_pizza_shop.model.Ingredients;
import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.Pizza;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.IngredientIDInvalid;
import webprogramming.project.online_pizza_shop.model.exceptions.PizzaNotFoundException;
import webprogramming.project.online_pizza_shop.service.IngredientsService;
import webprogramming.project.online_pizza_shop.service.OrderService;
import webprogramming.project.online_pizza_shop.service.PizzaService;
import webprogramming.project.online_pizza_shop.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/order"})
public class OrderController {

    private final PizzaService pizzaService;
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(PizzaService pizzaService, UserService userService,OrderService orderService) {
        this.pizzaService = pizzaService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping // It will show the Order Page and it will list the User, Orders, and pizzas from order
    public String orderPage(HttpServletRequest req,
                            Model model){

        String username=req.getRemoteUser(); // Get the users username

        //loadUserByUsername(req.getRemoteUser()) it will get the User
        User user = (User) this.userService.loadUserByUsername(username); // Gets the users info from username
        Order order = this.orderService.getActiveOrder(user.getUsername()); // Gets the order info from username

        model.addAttribute("user",this.userService.loadUserByUsername(username));
        model.addAttribute("order", this.orderService.getActiveOrder(username));
        model.addAttribute("pizzas", this.orderService.getActiveOrder(username).getPizza());
        model.addAttribute("bodyContent", "orderPage");
        return "master-template";
    }

    @PostMapping("/add/{id}") // It is used to add Pizza from Menu in Order
    public String addPizzaToOrder(@PathVariable Long id, HttpServletRequest req) {
        try {
            //req.getRemoteUser() it will get the users username
            //loadUserByUsername(req.getRemoteUser()) it will get the User from username
            User user = (User) this.userService.loadUserByUsername(req.getRemoteUser());

            this.orderService.addPizza(user.getUsername(), id);
            return "redirect:/order";
        } catch (RuntimeException exception) {
            return "redirect:/order?error=" + exception.getMessage();
        }
    }

    @PostMapping("/add-custom") // It is used to add Pizza from Custom in Order
    public String customPizza(@RequestParam(value = "ingredientIds", required = false) List<Long> ingredients,
                              @RequestParam(value = "pizzaSize", required = false) String pizzaSize,
                              @RequestParam(value = "customPizzaName", required = false) String pizzaName,
                              HttpServletRequest req,
                              Model model){

        double cost = 0.0;
        String username=req.getRemoteUser();//req.getRemoteUser() it will get the users username

        //loadUserByUsername(req.getRemoteUser()) it will get the User from username
        User user = (User) this.userService.loadUserByUsername(username); // Gets the users info from username

        cost = this.pizzaService.calculateCost(ingredients, pizzaSize); //Calculate the cost of Custom Pizza

        //It will add a Photo to the custom pizza
        String customPizzaUrl = "https://mk0pieologyc5tbrq0kb.kinstacdn.com/wp-content/uploads/2020/11/Screenshot_SIDES__SWEETS_%E2%80%93_pieology.com_-_Google_Chrome_247.png";

        this.pizzaService.save(pizzaName, pizzaSize, cost, customPizzaUrl, ingredients);// It is used to save the custom Pizza

        Pizza pizza = this.pizzaService.getPizzaFromName(pizzaName).orElseThrow(PizzaNotFoundException::new); // It will get the Pizza from Pizza Name

        addPizzaToOrder(pizza.getId(), req); // it will add the pizza to the Order
        return "redirect:/order";
    }

    @PostMapping("/make-order") // It will send the user to the confirm Page to confirm the order
    public String makeOrder(@RequestParam(value = "pizzaIds", required = false)  List<Long> pizzaIds,
                            @RequestParam(value = "delivery", required = false) String delivery,
                           HttpServletRequest req,
                           Model model){

        String username=req.getRemoteUser();//req.getRemoteUser() it will get the users username

        //loadUserByUsername(req.getRemoteUser()) it will get the User from username
        User user = (User) this.userService.loadUserByUsername(username);// Gets the users info from username
        Order order = this.orderService.getActiveOrder(user.getUsername());// Gets the order info from username

        double totalCost = 0.0;
        totalCost = this.orderService.calculateTotalCost(pizzaIds, delivery, order); // It will calculate the total cost of the Order

        model.addAttribute("totalCost",totalCost);
        model.addAttribute("order", order);
        model.addAttribute("bodyContent", "confirmOrderPage");
        return "master-template";
    }



    @GetMapping("/confirm")
    public String confirmOrder(@RequestParam(value = "cost", required = false) Double cost,
                               @RequestParam(value = "timeUntilPizzaArrives", required = false) String timeUntilPizzaArrives,
                               HttpServletRequest req){

        //req.getRemoteUser() it will get the users username
        //loadUserByUsername(req.getRemoteUser()) it will get the User from username
        User user = (User) this.userService.loadUserByUsername(req.getRemoteUser()); // gets User info from username

        Order order = this.orderService.getActiveOrder(user.getUsername()); // Gets the active order from username

        order.setCost(cost);
        order.setTimeUntilPizzaArrives(timeUntilPizzaArrives);

        this.orderService.save(order); // It will save the order

        //order = new Order(user);
        //user.order.add(order);

        return "redirect:/home";
    }
}

