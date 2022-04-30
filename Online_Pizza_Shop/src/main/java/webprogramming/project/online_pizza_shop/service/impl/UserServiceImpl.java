package webprogramming.project.online_pizza_shop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webprogramming.project.online_pizza_shop.model.Role;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.model.exceptions.InvalidUsernameOrPasswordException;
import webprogramming.project.online_pizza_shop.model.exceptions.PasswordsDoNotMatchException;
import webprogramming.project.online_pizza_shop.model.exceptions.UsernameAlreadyExistsException;
import webprogramming.project.online_pizza_shop.repository.UserRepository;
import webprogramming.project.online_pizza_shop.service.UserService;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String confirmPassword, String address, String ccn, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(confirmPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),address,ccn,role);
        return this.userRepository.save(user);
    }
}
