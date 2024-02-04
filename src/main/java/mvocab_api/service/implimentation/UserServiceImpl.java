package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.service.UsersResponse;
import mvocab_api.exeption.UserAlreadyExistException;
import mvocab_api.exeption.UserDoesNotExistException;
import mvocab_api.model.User;
import mvocab_api.repository.UserRepository;
import mvocab_api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UsersResponse findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userRepository.findAll(pageable);
        List<User> content = users.stream().map(User::toModel).collect(Collectors.toList());
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
    public UserEntity findById(Integer id) throws UserDoesNotExistException {
        return userRepository.findById(id).orElseThrow(UserDoesNotExistException::new);
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

    @Override
    public List<LangEntity> findLangsByUserId(Integer id) throws UserDoesNotExistException {
        findById(id);
        return userRepository.findLangsByUserId(id);
    }
}
