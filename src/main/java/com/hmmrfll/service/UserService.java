package com.hmmrfll.service;

import com.hmmrfll.model.User;

import java.util.Optional;

public interface UserService {
    public User findUserById(Long userId) throws Exception;
    public Optional<User> findUserByJwt(String token) throws Exception;

}
