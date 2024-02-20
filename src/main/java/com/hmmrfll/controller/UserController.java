package com.hmmrfll.controller;

import com.hmmrfll.model.User;
import com.hmmrfll.repository.UserRepository;
import com.hmmrfll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users/profile")
    public Optional<User> findUserByJwt(
            @RequestHeader("Authorization") String authorizationHeader
    ) throws Exception {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Извлечь токен, игнорируя "Bearer "
            return userService.findUserByJwt(token);
        } else {
            // Обработка случаев, когда заголовок Authorization отсутствует или имеет неверный формат
            throw new IllegalArgumentException("Invalid or missing Authorization header");
        }
    }

}
