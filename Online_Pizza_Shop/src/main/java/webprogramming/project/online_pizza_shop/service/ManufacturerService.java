package webprogramming.project.online_pizza_shop.service;

import webprogramming.project.online_pizza_shop.model.Manufacturer;
import webprogramming.project.online_pizza_shop.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Optional<Manufacturer> findById(Long id);
    List<Manufacturer> findAll();
    Optional<Manufacturer> save(String name, String CoO);
}
