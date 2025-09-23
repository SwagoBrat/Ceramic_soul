package org.example.testspring.services;

import org.example.testspring.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthorizedUserService {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        AuthorizedUserService.user = user;
    }
}
