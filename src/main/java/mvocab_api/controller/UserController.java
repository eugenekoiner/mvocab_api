package mvocab_api.controller;

import lombok.AllArgsConstructor;
import mvocab_api.entity.UserEntity;
import mvocab_api.service.EntityMapper;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")


public class UserController {
    private final UserService userService;

    // получить полный список пользователей с данными (с параметрами и пагинацией)
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @GetMapping
    public ResponseEntity<Object> findAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(userService.findAllUsers(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить данные о конкретном пользователе
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage(EntityMapper.INSTANCE.toUserForList(userService.findById(id)));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // изменить данные конкретного пользователя
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        try {
            return ResponseMessage.responseMessage(EntityMapper.INSTANCE.toUserForList(userService.updateUser(id, userEntity)));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить конкретного пользователя
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("message", userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить список языков, которые изучает конкретный пользователь (с пагинацией)
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @GetMapping("{id}/langs")
    public ResponseEntity<Object> findLangsByUserId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("langs", userService.findLangsByUserId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // добавить новый язык для изучения пользователем
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @PostMapping("{id}/langs/{langId}")
    public ResponseEntity<Object> addLangByUserId(@PathVariable Integer id, @PathVariable Integer langId) {
        try {
            return ResponseMessage.responseMessage("message", userService.addLangByUserId(id, langId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить язык из изучаемых у конкретного пользователя
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @DeleteMapping("{id}/langs/{langId}")
    public ResponseEntity<Object> deleteLangByUserId(@PathVariable Integer id, @PathVariable Integer langId) {
        try {
            return ResponseMessage.responseMessage("message", userService.deleteLangByUserId(id, langId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }


    // получить список всех изученных слов пользователя (с пагинацией и сортировкой по дате изучения)
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @GetMapping("{id}/words")
    public ResponseEntity<Object> findWordsByUserId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("words", userService.findWordsByUserId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // добавить слово в словарь пользователя
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @PostMapping("{id}/words/{wordId}")
    public ResponseEntity<Object> addWordByUserId(@PathVariable Integer id, @PathVariable Integer wordId) {
        try {
            return ResponseMessage.responseMessage("message", userService.addWordByUserId(id, wordId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить слово из словаря пользователя
    @PreAuthorize("@userServiceImpl.userHasAccessToHisResources(#id)")
    @DeleteMapping("{id}/words/{wordId}")
    public ResponseEntity<Object> deleteWordByUserId(@PathVariable Integer id, @PathVariable Integer wordId) {
        try {
            return ResponseMessage.responseMessage("message", userService.deleteWordByUserId(id, wordId));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }



}

