package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webprogramming.project.online_pizza_shop.service.IngredientsService;
import webprogramming.project.online_pizza_shop.service.PizzaService;

@Controller
@RequestMapping( value = {"/custom"})
public class CustomPizzaController {

    private final IngredientsService ingredientsService;

    public CustomPizzaController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping //It will show the Custom page and it will list all the Ingredients
    public String getCustomPizzaPage(Model model){
        model.addAttribute("ingredients", this.ingredientsService.findAll());
        model.addAttribute("bodyContent","customPizzaPage");
        return "master-template";
    }
}
