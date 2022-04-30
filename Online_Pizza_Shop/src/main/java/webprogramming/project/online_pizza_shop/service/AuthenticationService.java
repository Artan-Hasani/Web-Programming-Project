package webprogramming.project.online_pizza_shop.service;

import webprogramming.project.online_pizza_shop.model.User;

public interface AuthenticationService {
    User login(String username, String password);
}
