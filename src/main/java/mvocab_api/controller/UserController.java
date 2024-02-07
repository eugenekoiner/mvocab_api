package mvocab_api.controller;

import lombok.AllArgsConstructor;
import mvocab_api.entity.UserEntity;
import mvocab_api.model.User;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userService.registerUser(userEntity);
            return ResponseMessage.responseMessage("id", User.toModel(user).getId());
        } catch (DataIntegrityViolationException e) {
            return ResponseMessage.responseMessage("message", "duplicate phone entry");
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(userService.findAllUsers(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(User.toModel(userService.findById(id)));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        try {
            return ResponseMessage.responseMessage(userService.updateUser(id, userEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseMessage.responseMessage("message", "duplicate entry");
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("message", userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @GetMapping("{id}/langs")
    public ResponseEntity<Object> findLangsByUserId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(userService.findLangsByUserId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @PostMapping("{id}/langs/{langId}")
    public ResponseEntity<Object> addLangByUserId(@PathVariable Integer id, @PathVariable Integer langId) {
        try {
            return ResponseMessage.responseMessage(userService.addLangByUserId(id, langId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @DeleteMapping("{id}/langs/{langId}")
    public ResponseEntity<Object> deleteLangByUserId(@PathVariable Integer id, @PathVariable Integer langId) {
        try {
            return ResponseMessage.responseMessage(userService.deleteLangByUserId(id, langId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

}
