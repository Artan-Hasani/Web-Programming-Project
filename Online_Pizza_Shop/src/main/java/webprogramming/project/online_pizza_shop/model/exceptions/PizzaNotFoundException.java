package webprogramming.project.online_pizza_shop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException() {
        super(String.format("Pizza is not found"));
    }
}

