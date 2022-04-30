package webprogramming.project.online_pizza_shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cost;

    private String timeUntilPizzaArrives;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Pizza> pizza;

    public Order() {
    }

    public Order(Double cost, String timeUntilPizzaArrives, User user) {
        this.cost = cost;
        this.timeUntilPizzaArrives = timeUntilPizzaArrives;
        this.pizza = new ArrayList<>();
        this.user = user;
    }
    public Order(User user) {
        this.cost = 0.0;
        this.timeUntilPizzaArrives = "";
        this.pizza = new ArrayList<>();
        this.user = user;
    }
}
