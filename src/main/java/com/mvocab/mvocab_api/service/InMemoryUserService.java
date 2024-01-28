package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.model.User;
import com.mvocab.mvocab_api.repository.InMemoryUserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryUserService implements UserService {

    private final InMemoryUserDAO repository;
    @Override
    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    @Override
    public User saveUser(User user) {
        return repository.saveUser(user);
    }

    @Override
    public User findById(int id) {
        return repository.findById(id);
    }

    @Override
    public User updateUser(int id, User user) {
        return repository.updateUser(id, user);
    }

    @Override
    public String deleteUser(int id) {
        return repository.deleteUser(id);
    }
}
