package webprogramming.project.online_pizza_shop.service;

import webprogramming.project.online_pizza_shop.model.Ingredients;
import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.Pizza;
import webprogramming.project.online_pizza_shop.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order addPizza(String username, Long id);

    Order getActiveOrder(String username);

    Optional<Order> save(Order order);

    double calculateTotalCost(List<Long> pizzaIds, String delivery, Order order);
}
