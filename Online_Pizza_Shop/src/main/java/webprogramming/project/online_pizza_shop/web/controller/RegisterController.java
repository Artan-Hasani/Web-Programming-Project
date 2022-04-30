package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprogramming.project.online_pizza_shop.model.Role;
import webprogramming.project.online_pizza_shop.model.exceptions.InvalidArgumentsException;
import webprogramming.project.online_pizza_shop.model.exceptions.PasswordsDoNotMatchException;
import webprogramming.project.online_pizza_shop.service.AuthenticationService;
import webprogramming.project.online_pizza_shop.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public RegisterController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping // It will show the Register Page
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("bodyContent","registerPage");
        return "master-template";
    }

    @PostMapping // It will Register the user
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String address,
                           @RequestParam String ccn,
                           @RequestParam Role role) {
        try{
            this.userService.register(username, password, confirmPassword, address, ccn, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register";
        }
    }
}
