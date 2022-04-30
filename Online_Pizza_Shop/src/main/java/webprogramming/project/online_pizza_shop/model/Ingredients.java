package webprogramming.project.online_pizza_shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double cost;

    @ManyToOne
    private Manufacturer manufacturer;

    public Ingredients() {
    }

    public Ingredients(String name, Double cost, Manufacturer manufacturer) {
        this.name = name;
        this.cost = cost;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }
}
