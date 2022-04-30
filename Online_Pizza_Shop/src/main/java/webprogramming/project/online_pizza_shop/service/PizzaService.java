package webprogramming.project.online_pizza_shop.service;

import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.Pizza;
import webprogramming.project.online_pizza_shop.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    List<Pizza> findAll();

    Optional<Pizza> findById(Long id);

    Optional<Pizza> findByName(String name);

    Optional<Pizza> save(String name, String size, Double cost, String url, List<Long> ingredientsIds);

    Optional<Pizza> getPizzaFromName(String pizzaName);

    double calculateCost(List<Long> ingredients, String pizzaSize);

    boolean checkDiscount(User user, Order order);
}
