package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();
    User saveUser(User user);
    Optional<User> findById(Integer id);
    User updateUser(Integer id, User user);
    String deleteUser(Integer id);
}
