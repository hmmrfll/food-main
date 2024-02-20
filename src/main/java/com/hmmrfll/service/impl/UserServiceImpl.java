package com.hmmrfll.service.impl;

import com.hmmrfll.auth.service.JwtService;
import com.hmmrfll.model.User;
import com.hmmrfll.repository.UserRepository;
import com.hmmrfll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("User not found with id: " + userId);
    }

    @Override
    public Optional<User> findUserByJwt(String token) throws Exception {
        String email = jwtService.extractUsername(token);
        if(email == null){
            throw new Exception("provide valid jwt token...");
        }
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new Exception("User not found with email: " + email);
        }
        return user;
    }
}
