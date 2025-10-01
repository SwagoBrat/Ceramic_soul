package org.example.testspring.controllers;

import org.example.testspring.components.JwtUtility;
import org.example.testspring.model.User;
import org.example.testspring.repository.UserRepository;
import org.example.testspring.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:5173") //айпі з якого приймаю запити
@RestController //REST (для JSON)
@RequestMapping("/api") //Який шлях у класу на бекенді (localhost:8080/api)
public class LoginController {

    @Autowired
    JwtUtility jwtUtility;

    @Autowired //Прікольна штука, ініціалізує об'єкт на етапі компіляції
    UserRepository userRepository;

    @PostMapping("/login") //POST (оскільки передаю та аналогічно до 13 рядка)
    public ResponseEntity<?> login(@RequestBody User user) { //ResponeEntity - для оброки запитів. @RequestBody - Spring формує клас типу User з даних JSON
        if (userRepository.existsByEmail(user.getEmail())) {
            User mongoUser = userRepository.findByEmail(user.getEmail());
            if (mongoUser.getPassword().equals(HashService.hash(user.getPassword()))) {
                String token = jwtUtility.generateToken(mongoUser.getEmail());
                return ResponseEntity.ok(Map.of("token", token));
            }
            return ResponseEntity.badRequest().body("Wrong password");
        }
        return ResponseEntity.badRequest().body("Wrong email");
    }
    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring("Bearer ".length());
        if (jwtUtility.validateToken(token)) {
            User user = userRepository.findByEmail(jwtUtility.getDataFromToken(token));
            return ResponseEntity.ok(user.getName());
        } else {
            return ResponseEntity.badRequest().body("Wrong token");
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring("Bearer ".length());
        if (jwtUtility.validateToken(token)) {
            String email = jwtUtility.getDataFromToken(token);
            return ResponseEntity.ok(userRepository.findByEmail(email));
        }
        return ResponseEntity.badRequest().body("Wrong token");
    }
}
