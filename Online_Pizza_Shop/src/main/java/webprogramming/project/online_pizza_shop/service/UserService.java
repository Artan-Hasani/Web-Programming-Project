package webprogramming.project.online_pizza_shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import webprogramming.project.online_pizza_shop.model.Role;
import webprogramming.project.online_pizza_shop.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String confirmPassword, String address, String ccn, Role role);
}
