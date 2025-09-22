package org.example.testspring.controllers;

import org.example.testspring.model.User;
import org.example.testspring.repository.UserRepository;
import org.example.testspring.services.AuthorizedUserService;
import org.example.testspring.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            User mongoUser = userRepository.findByEmail(user.getEmail());
            if (mongoUser.getPassword().equals(HashService.hash(user.getPassword()))) {
                String name = mongoUser.getName();
                AuthorizedUserService.setUser(mongoUser);
                return ResponseEntity.ok(name);
            }
            return ResponseEntity.badRequest().body("Wrong password");
        }
        return ResponseEntity.badRequest().body("Not found!");
    }
    @GetMapping("/check")
    public ResponseEntity<String> check() {
        if (AuthorizedUserService.getUser() != null) {
            return ResponseEntity.ok(AuthorizedUserService.getUser().getName());
        }
        return ResponseEntity.badRequest().build();
    }
}
