package com.task313.service;

import com.task313.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    User read(long id);
    List<User> readAll();
    void update(User user);
    void delete(long id);
}
