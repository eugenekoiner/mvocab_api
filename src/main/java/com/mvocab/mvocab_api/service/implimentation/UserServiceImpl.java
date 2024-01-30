package com.mvocab.mvocab_api.service.implimentation;

import com.mvocab.mvocab_api.entity.UserEntity;
import com.mvocab.mvocab_api.exeption.UserAlreadyExistException;
import com.mvocab.mvocab_api.repository.UserRepository;
import com.mvocab.mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    //todo: перенести в auth
    public UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException {
        if (userRepository.findById(userEntity.getId()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity updatedUserEntity) {
        return userRepository.updateUserById(id, updatedUserEntity) > 0 ? userRepository.findById(id).orElse(null) : null;
    }

    @Override
    public String deleteUser(Integer id) {
        return userRepository.deleteUserById(id) > 0 ? "removed" : "UserEntity does not exist";
    }
}
