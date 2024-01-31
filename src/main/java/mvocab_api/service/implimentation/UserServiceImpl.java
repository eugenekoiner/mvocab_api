package mvocab_api.service.implimentation;

import mvocab_api.entity.UserEntity;
import mvocab_api.entity.UsersResponse;
import mvocab_api.exeption.UserAlreadyExistException;
import mvocab_api.exeption.UserDoesNotExistException;
import mvocab_api.repository.UserRepository;
import mvocab_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UsersResponse findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userRepository.findAll(pageable);
        List<UserEntity> content = users.getContent();
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setContent(content);
        usersResponse.setPage(users.getNumber());
        usersResponse.setSize(users.getSize());
        usersResponse.setTotalElements(users.getTotalElements());
        usersResponse.setTotalPages(users.getTotalPages());
        usersResponse.setLast(users.isLast());

        return usersResponse;

    }

    @Override
    public UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException {
        if (userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity updatedUserEntity) throws UserDoesNotExistException {
        if (userRepository.updateUserById(id, updatedUserEntity) < 1) {
            throw new UserDoesNotExistException();
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteUser(Integer id) throws UserDoesNotExistException {
        if (userRepository.deleteUserById(id) < 1) {
            throw new UserDoesNotExistException();
        }
        return "removed";
    }
}
