package org.example.testspring.controllers;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.testspring.model.User;
import org.example.testspring.repository.UserRepository;
import org.example.testspring.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/reg")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Вже є");
        }
        user.setPassword(HashService.hash(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user).getPassword());
    }
}
