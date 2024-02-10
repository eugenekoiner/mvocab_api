package mvocab_api.controller;

import lombok.AllArgsConstructor;
import mvocab_api.entity.UserEntity;
import mvocab_api.model.User;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    // создать нового пользователя (временно)
    @PostMapping("register")
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userService.registerUser(userEntity);
            return ResponseMessage.responseMessage("id", User.toModel(user).getId());
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить полный список пользователей с данными (с параметрами и пагинацией)
    @GetMapping
    public ResponseEntity<Object> findAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(userService.findAllUsers(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить данные о конкретном пользователе
    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(User.toModel(userService.findById(id)));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // изменить данные конкретного пользователя
    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        try {
            return ResponseMessage.responseMessage(User.toModel(userService.updateUser(id, userEntity)));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить конкретного пользователя
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("message", userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить список языков, которые изучает конкретный пользователь (с пагинацией)
    @GetMapping("{id}/langs")
    public ResponseEntity<Object> findLangsByUserId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(userService.findLangsByUserId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // добавить новый язык для изучения пользователем
    @PostMapping("{id}/langs/{langId}")
    public ResponseEntity<Object> addLangByUserId(@PathVariable Integer id, @PathVariable Integer langId) {
        try {
            return ResponseMessage.responseMessage("message", userService.addLangByUserId(id, langId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить язык из изучаемых у конкретного пользователя
    @DeleteMapping("{id}/langs/{langId}")
    public ResponseEntity<Object> deleteLangByUserId(@PathVariable Integer id, @PathVariable Integer langId) {
        try {
            return ResponseMessage.responseMessage("message", userService.deleteLangByUserId(id, langId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }


    // получить список всех изученных слов пользователя (с пагинацией и сортировкой по дате изучения)
    @GetMapping("{id}/words")
    public ResponseEntity<Object> findWordsByUserId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(userService.findWordsByUserId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // добавить слово в словарь пользователя
    @PostMapping("{id}/words/{wordId}")
    public ResponseEntity<Object> addWordByUserId(@PathVariable Integer id, @PathVariable Integer wordId) {
        try {
            return ResponseMessage.responseMessage("message", userService.addWordByUserId(id, wordId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }



}
