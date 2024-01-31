package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.entity.UserEntity;
import com.mvocab.mvocab_api.exeption.UserAlreadyExistException;
import com.mvocab.mvocab_api.exeption.UserDoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAllUsers(int page, int size);
    UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException;
    Optional<UserEntity> findById(Integer id);
    UserEntity updateUser(Integer id, UserEntity userEntity) throws UserDoesNotExistException;
    String deleteUser(Integer id) throws UserDoesNotExistException;
}
