package com.mvocab.mvocab_api.service.implimentation;

import com.mvocab.mvocab_api.model.User;
import com.mvocab.mvocab_api.repository.UserRepository;
import com.mvocab.mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    //todo: перенести в auth
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.updateUserById(id, updatedUser) > 0 ? userRepository.findById(id).orElse(null) : null;
        }

    @Override
    public String deleteUser(Integer id) {
      return userRepository.deleteUserById(id) > 0 ? "removed" : "User does not exist";
    }
}
