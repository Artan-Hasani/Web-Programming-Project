package webprogramming.project.online_pizza_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import webprogramming.project.online_pizza_shop.model.User;
import webprogramming.project.online_pizza_shop.repository.UserRepository;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public FacebookConnectionSignup(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(passwordEncoder.encode("random"));
        userRepository.save(user);
        return user.getUsername();
    }

}
