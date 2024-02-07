package mvocab_api.service;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.exeption.UserAlreadyExistException;
import mvocab_api.exeption.UserDoesNotExistException;

import java.util.List;

public interface UserService {

    UsersResponse findAllUsers(int page, int size);

    UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException;

    UserEntity findById(Integer id) throws UserDoesNotExistException;

    UserEntity updateUser(Integer id, UserEntity userEntity) throws UserDoesNotExistException;

    String deleteUser(Integer id) throws UserDoesNotExistException;

    List<LangEntity> findLangsByUserId(Integer id) throws UserDoesNotExistException;

    Object addLangByUserId(Integer id, Integer langId);

    Object deleteLangByUserId(Integer id, Integer langId);
}
