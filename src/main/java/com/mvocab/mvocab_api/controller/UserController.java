package com.mvocab.mvocab_api.controller;

import com.mvocab.mvocab_api.model.User;
import com.mvocab.mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService service;

    @GetMapping
    public List<User> findAllUsers () {
        return service.findAllUsers();
    }
    @PostMapping("save_user")
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable int id) {
        return service.deleteUser(id);
    }

}
