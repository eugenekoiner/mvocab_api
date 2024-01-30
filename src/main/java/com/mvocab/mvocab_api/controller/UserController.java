package com.mvocab.mvocab_api.controller;

import com.mvocab.mvocab_api.ResponseMessage;
import com.mvocab.mvocab_api.entity.UserEntity;
import com.mvocab.mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.mvocab.mvocab_api.ResponseMessage.responseMessage;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserEntity> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("register")
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userService.registerUser(userEntity);
            return responseMessage("id", user.getId());
        } catch (Exception e) {
            return responseMessage("message", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            return responseMessage(userService.findById(id));
        } catch (Exception e) {
            return responseMessage("message", e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        try {
            return responseMessage(userService.updateUser(id, userEntity));
        } catch (DataIntegrityViolationException e) {
            return responseMessage("message", "duplicate entry");
        } catch (Exception e) {
            return responseMessage("message", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        try {
            return responseMessage("message", userService.deleteUser(id));
        } catch (Exception e) {
            return responseMessage("message", e.getMessage());
        }
    }

}
