package com.mvocab.mvocab_api.repository;

import com.mvocab.mvocab_api.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryUserDAO {

    private final List<User> USERS = new ArrayList<>();

    public List<User> findAllUsers() {
        return USERS;
    }

    public User saveUser(User user) {
        USERS.add(user);
        return user;
    }

    public User findByEmail(String email) {
        return USERS.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public User findById(int id) {
        return USERS.stream()
                .filter(user -> user.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public User updateUser(int id, User updatedUser) {
        User user = findById(id);
        var userIndex = IntStream.range(0, USERS.size())
                .filter(index -> USERS.get(index).getId().equals(user.getId()))
                .findFirst()
                .orElse(-1);
        if (userIndex > -1) {
            USERS.set(userIndex, updatedUser);
            return updatedUser;
        }
        return null;
    }

    public String deleteUser(int id) {
        String message;
        var user = findById(id);
        if (user != null) {
            USERS.remove(user);
            message = "removed";
        } else {
            message = "user not exist";
        }
        return message;

    }
}
