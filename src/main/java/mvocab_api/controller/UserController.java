package mvocab_api.controller;

import mvocab_api.entity.UserEntity;
import mvocab_api.entity.UsersResponse;
import mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import mvocab_api.entity.ResponseMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static mvocab_api.entity.ResponseMessage.responseMessage;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(userService.findAllUsers(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @PostMapping("register")
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userService.registerUser(userEntity);
            return ResponseMessage.responseMessage("id", user.getId());
        } catch (DataIntegrityViolationException e) {
            return ResponseMessage.responseMessage("message", "duplicate entry");
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(userService.findById(id));
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

}
