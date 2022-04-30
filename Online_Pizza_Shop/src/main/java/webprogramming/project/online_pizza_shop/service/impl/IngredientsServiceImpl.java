package webprogramming.project.online_pizza_shop.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.online_pizza_shop.bootstrap.DataHolder;
import webprogramming.project.online_pizza_shop.model.Ingredients;
import webprogramming.project.online_pizza_shop.model.Manufacturer;
import webprogramming.project.online_pizza_shop.model.exceptions.IngredientIDInvalid;
import webprogramming.project.online_pizza_shop.model.exceptions.ManufacturerNotFoundException;
import webprogramming.project.online_pizza_shop.model.exceptions.PizzaNotFoundException;
import webprogramming.project.online_pizza_shop.repository.IngredientsRepository;
import webprogramming.project.online_pizza_shop.repository.ManufacturerRepository;
import webprogramming.project.online_pizza_shop.service.IngredientsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private final ManufacturerRepository manufacturerRepository;
    private final IngredientsRepository ingredientsRepository;

    public IngredientsServiceImpl(ManufacturerRepository manufacturerRepository, IngredientsRepository ingredientsRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Optional<Ingredients> findById(Long id) {
        return this.ingredientsRepository.findById(id);
    }

    @Override
    public List<Ingredients> findAll() {
        return this.ingredientsRepository.findAll();
    }

    @Override
    public Optional<Ingredients> save(String name, Double cost, Long manId) {
        if(name.equals("")){
            throw new IngredientIDInvalid();
        }
        Manufacturer manufacturer = this.manufacturerRepository.findById(manId).orElseThrow(() -> new ManufacturerNotFoundException(manId));
        this.ingredientsRepository.deleteByName(name);
        return Optional.of(this.ingredientsRepository.save(new Ingredients(name, cost, manufacturer)));
    }
}
