package webprogramming.project.online_pizza_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webprogramming.project.online_pizza_shop.model.Order;
import webprogramming.project.online_pizza_shop.model.User;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}
