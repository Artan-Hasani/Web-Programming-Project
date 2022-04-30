package webprogramming.project.online_pizza_shop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IngredientIDInvalid extends RuntimeException{
    public IngredientIDInvalid() {
        super(String.format("Ingredient is not found"));
    }
}
