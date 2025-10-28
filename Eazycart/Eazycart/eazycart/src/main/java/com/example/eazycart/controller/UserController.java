package com.example.eazycart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.eazycart.model.User;
import com.example.eazycart.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/test")
    public String testApi() {
        return "API is working! Use POST /signup or POST /login.";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }
        userRepository.save(user);
        return " Account created!";
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (dbUser != null) {
            return "Login successful! Welcome " + dbUser.getName();
        }
        return "Invalid email or password";
    }
}
