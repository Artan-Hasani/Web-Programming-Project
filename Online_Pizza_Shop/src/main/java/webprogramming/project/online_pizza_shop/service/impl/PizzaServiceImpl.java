package webprogramming.project.online_pizza_shop.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.online_pizza_shop.bootstrap.DataHolder;
import webprogramming.project.online_pizza_shop.model.Ingredients;
import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.Pizza;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.IngredientIDInvalid;
import webprogramming.project.online_pizza_shop.model.exceptions.PizzaNotFoundException;
import webprogramming.project.online_pizza_shop.repository.IngredientsRepository;
import webprogramming.project.online_pizza_shop.repository.PizzaRepository;
import webprogramming.project.online_pizza_shop.service.IngredientsService;
import webprogramming.project.online_pizza_shop.service.PizzaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final IngredientsRepository ingredientsRepository;
    private final IngredientsService ingredientsService;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, IngredientsRepository ingredientsRepository, IngredientsService ingredientsService) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientsService = ingredientsService;
    }

    @Override
    public List<Pizza> findAll() {
        return this.pizzaRepository.findAll();
    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return this.pizzaRepository.findById(id);
    }

    @Override
    public Optional<Pizza> findByName(String name) {
        return this.pizzaRepository.findByName(name);
    }

    @Override
    public Optional<Pizza> save(String name, String size, Double cost, String url, List<Long> ingredientsIds) {

        List<Ingredients> ingredients = new ArrayList<>();

        for (Long ingredientsId : ingredientsIds) {
            ingredients.add(this.ingredientsRepository.findById(ingredientsId).orElseThrow(IngredientIDInvalid::new));
        }
        this.pizzaRepository.deleteByName(name);
        return Optional.of(this.pizzaRepository.save(new Pizza(name, size, cost, url, ingredients)));
    }

    @Override
    public Optional<Pizza> getPizzaFromName(String pizzaName) {
        return Optional.of(this.pizzaRepository.findByName(pizzaName)).orElseThrow(PizzaNotFoundException::new);
    }

    @Override
    public double calculateCost(List<Long> ingredients, String pizzaSize) {
        double cost = 0.0;
        if(pizzaSize.equals("Large")) {
            cost=200;
        }else if(pizzaSize.equals("Medium")){
            cost=150;
        }else{
            cost=100;
        }
        List<Ingredients> ingredientsList = new ArrayList<>();
        for (Long aLong : ingredients) {
            ingredientsList.add(this.ingredientsService.findById(aLong).orElseThrow(IngredientIDInvalid::new));
        }
        for (Ingredients ing: ingredientsList) {
            cost+=ing.getCost();
        }
        return cost;
    }

    @Override
    public boolean checkDiscount(User user, Order order) {
        return (order.getPizza().size() % 2 == 0 && order.getPizza().size() != 0);
    }
}
