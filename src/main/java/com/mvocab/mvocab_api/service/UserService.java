package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.entity.UserEntity;
import com.mvocab.mvocab_api.exeption.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAllUsers();
    UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException;
    Optional<UserEntity> findById(Integer id);
    UserEntity updateUser(Integer id, UserEntity userEntity);
    String deleteUser(Integer id);
}
