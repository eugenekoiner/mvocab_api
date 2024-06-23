package mvocab_api.service;

import mvocab_api.dto.AuthDto;
import mvocab_api.dto.AuthResponseDto;
import mvocab_api.dto.LoginDto;
import mvocab_api.entity.UserEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;

public interface AuthService {

    UserEntity registerUser(AuthDto authDto) throws AlreadyExistException, DoesNotExistException;
    AuthResponseDto loginUser(LoginDto loginDto) throws DoesNotExistException;
}
