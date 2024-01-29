package com.mvocab.mvocab_api.controller;

import com.mvocab.mvocab_api.model.User;
import com.mvocab.mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> findAllUsers () {
        return userService.findAllUsers();
    }
    @PostMapping("save_user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}
