package webprogramming.project.online_pizza_shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String size;

    private Double cost;

    private String url;

    @ManyToMany
    private List<Ingredients> ingredients;

    public Pizza() {
    }

    public Pizza(String name, String size, Double cost, String url, List<Ingredients> ingredients) {
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.url = url;
        this.ingredients = ingredients;
    }

    public String getName() {
    return name;
    }
}
