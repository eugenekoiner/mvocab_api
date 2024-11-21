package mvocab_api.controller;

import lombok.AllArgsConstructor;
import mvocab_api.dto.AuthDto;
import mvocab_api.dto.LoginDto;
import mvocab_api.entity.UserEntity;
import mvocab_api.service.AuthService;
import mvocab_api.service.EntityMapper;
import mvocab_api.service.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    // зарегистрировать нового пользователя
    @PostMapping("register")
    public ResponseEntity<Object> registerUser(@RequestBody AuthDto authDto) {
        try {
            UserEntity user = authService.registerUser(authDto);
            return ResponseMessage.responseMessage("id", EntityMapper.INSTANCE.toUserForList(user).getId());
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // залогинить пользователя
    @PostMapping("login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDto loginDto) {
        try {
            return ResponseMessage.responseMessage("message", authService.loginUser(loginDto));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }


}

