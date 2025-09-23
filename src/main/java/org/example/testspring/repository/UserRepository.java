package org.example.testspring.repository;

import org.example.testspring.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> { //Монго репозиторій
    boolean existsByEmail(String email); //Це просто ахуєть, Spring просто ЗА НАЗВОЮ МЕТОДУ сам придумує йому логіку
    User findByEmail(String email);
}
