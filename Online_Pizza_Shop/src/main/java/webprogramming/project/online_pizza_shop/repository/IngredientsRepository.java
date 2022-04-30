package webprogramming.project.online_pizza_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webprogramming.project.online_pizza_shop.model.Ingredients;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    //Function deleteByName() does not work
    Optional<Ingredients> deleteByName(String name);
}
