package webprogramming.project.online_pizza_shop.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.InvalidArgumentsException;
import webprogramming.project.online_pizza_shop.model.exceptions.InvalidUserCredentialsException;
import webprogramming.project.online_pizza_shop.repository.UserRepository;
import webprogramming.project.online_pizza_shop.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
