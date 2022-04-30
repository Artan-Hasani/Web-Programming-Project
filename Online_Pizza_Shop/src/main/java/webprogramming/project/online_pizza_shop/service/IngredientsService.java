package webprogramming.project.online_pizza_shop.service;

import webprogramming.project.online_pizza_shop.model.Ingredients;
import webprogramming.project.online_pizza_shop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface IngredientsService {

    Optional<Ingredients> findById(Long id);
    List<Ingredients> findAll();
    Optional<Ingredients> save(String name, Double cost, Long manId);
}
