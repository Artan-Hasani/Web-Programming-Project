package webprogramming.project.online_pizza_shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.InvalidUserCredentialsException;
import webprogramming.project.online_pizza_shop.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping // It will show the login Page
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","loginPage");
        return "master-template";
    }

    @PostMapping // It will login the user
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authenticationService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception) {
            return "loginPage";
        }
    }
}

