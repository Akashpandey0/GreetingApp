package com.example.GreetingApp.service;

import com.example.greeting_app.model.User;
import com.example.greeting_app.repository.UserRepository;
import com.example.greeting_app.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Password Hashing
        return userRepository.save(user);
    }

    public String loginUser(User user) {
        Optional<User> user1 = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        boolean isMatch = user1.isPresent() && passwordEncoder.matches(user.getPassword(), user1.get().getPassword());
        if(isMatch){
            String token = jwtUtil.generateToken(user1.get().getEmail());
            //return token, and userInfo
            return "Congratulation! You have been logged in successfully! Your token is: " + token;
        }
        return "Invalid email or password";
    }
}