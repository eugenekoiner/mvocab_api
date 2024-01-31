package mvocab_api.service;

import mvocab_api.entity.UserEntity;
import mvocab_api.entity.UsersResponse;
import mvocab_api.exeption.UserAlreadyExistException;
import mvocab_api.exeption.UserDoesNotExistException;

import java.util.Optional;

public interface UserService {

    UsersResponse findAllUsers(int page, int size);
    UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException;
    Optional<UserEntity> findById(Integer id);
    UserEntity updateUser(Integer id, UserEntity userEntity) throws UserDoesNotExistException;
    String deleteUser(Integer id) throws UserDoesNotExistException;
}
