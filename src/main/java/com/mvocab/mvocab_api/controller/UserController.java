package com.mvocab.mvocab_api.controller;

import com.mvocab.mvocab_api.entity.UserEntity;
import com.mvocab.mvocab_api.exeption.UserAlreadyExistException;
import com.mvocab.mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserEntity> findAllUsers () {
        return userService.findAllUsers();
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userService.registerUser(userEntity);
            return ResponseEntity.ok("{\"id\":" + user.getId() + "}");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        return userService.updateUser(id, userEntity);
    }

    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}
