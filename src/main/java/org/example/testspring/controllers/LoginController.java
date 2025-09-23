package org.example.testspring.controllers;

import org.example.testspring.model.User;
import org.example.testspring.repository.UserRepository;
import org.example.testspring.services.AuthorizedUserService;
import org.example.testspring.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173") //айпі з якого приймаю запити
@RestController //REST (для JSON)
@RequestMapping("/api") //Який шлях у класу на бекенді (localhost:8080/api(
public class LoginController {

    @Autowired //Прікольна штука, ініціалізує об'єкт на етапі компіляції
    UserRepository userRepository;

    @PostMapping("/login") //POST (оскільки передаю та аналогічно до 13 рядка)
    public ResponseEntity<String> login(@RequestBody User user) { //ResponeEntity - для оброки запитів. @RequestBody - Spring формує клас типу User з даних JSON
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
