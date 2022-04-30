package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController{

    @GetMapping // it will show the home page where you can choose if you whant to order Pizza from Menu or Custom Pizza
    public String getHomePage(Model model){
        model.addAttribute("bodyContent","homePage");
        return "master-template";
    }



}
