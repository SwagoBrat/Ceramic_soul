package org.example.testspring.controllers;

import org.example.testspring.model.User;
import org.example.testspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@CrossOrigin("http://localhost:5175")
@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/reg")
    public ResponseEntity<String> register(@RequestBody User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(user.getPassword().getBytes());
        user.setPassword(HexFormat.of().formatHex(digest));
        return ResponseEntity.ok(userRepository.save(user).getPassword());
    }
}
