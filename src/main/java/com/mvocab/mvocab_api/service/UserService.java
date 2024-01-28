package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    User saveUser(User user);
    User findById(int id);
    User updateUser(int id, User user);
    String deleteUser(int id);


}
