package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void delete(User user);
}
