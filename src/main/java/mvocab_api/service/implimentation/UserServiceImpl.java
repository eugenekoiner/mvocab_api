package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.UserList;
import mvocab_api.repository.UserRepository;
import mvocab_api.service.EntityMapper;
import mvocab_api.service.PaginationResponse;
import mvocab_api.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public PaginationResponse findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> usersPage = userRepository.findAll(pageable);
        List<UserList> content = usersPage.stream().map(EntityMapper.INSTANCE::toUserForList).collect(Collectors.toList());
        return new PaginationResponse<>(new PageImpl<>(content, pageable, usersPage.getTotalElements()));
    }

    @Override
    public UserEntity findById(Integer id) throws DoesNotExistException {
        return userRepository.findById(id).orElseThrow(() -> new DoesNotExistException("user"));
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity updatedUserEntity) throws DoesNotExistException, AlreadyExistException {
        try {
            if (userRepository.updateUserById(id, updatedUserEntity) < 1) {
                throw new DoesNotExistException("user");
            }
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistException("user");
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteUser(Integer id) throws DoesNotExistException {
        if (userRepository.deleteUserById(id) < 1) {
            throw new DoesNotExistException("user");
        }
        return "removed";
    }

    @Override
    public List<LangEntity> findLangsByUserId(Integer id) throws DoesNotExistException {
        findById(id);
        return userRepository.findLangsByUserId(id);
    }

    @Override
    public Object addLangByUserId(Integer id, Integer langId) throws Exception {
        try {
            userRepository.addLangByUserId(id, langId);
        } catch (DataIntegrityViolationException e) {
            throw (e.getMessage().toLowerCase().contains("duplicate")) ? new AlreadyExistException("lang") : new DoesNotExistException("user", "lang");
        }
        return "success";
    }

    @Override
    public String deleteLangByUserId(Integer id, Integer langId) throws DoesNotExistException {
        if (userRepository.deleteLangByUserId(id, langId) < 1) {
            throw new DoesNotExistException("lang");
        }
        return "removed";
    }

    @Override
    public List<WordEntity> findWordsEntitesByUserId(Integer id) throws DoesNotExistException {
        findById(id);
        return userRepository.findWordsByUserId(id);
    }

    @Override
    public Object findWordsByUserId(Integer id) throws DoesNotExistException {
        findById(id);
        List<WordEntity> wordLists = userRepository.findWordsByUserId(id);
        return wordLists.stream().map(WordEntity::getWord).toList();
    }


    @Override
    public Object addWordByUserId(Integer id, Integer wordId) throws Exception {
        try {
            userRepository.addWordByUserId(id, wordId);
        } catch (DataIntegrityViolationException e) {
            throw (e.getMessage().toLowerCase().contains("duplicate")) ? new AlreadyExistException("word") : new DoesNotExistException("user", "word");
        }
        return "success";
    }

    @Override
    public String deleteWordByUserId(Integer id, Integer wordId) throws DoesNotExistException {
        if (userRepository.deleteWordByUserId(id, wordId) < 1) {
            throw new DoesNotExistException("word");
        }
        return "removed";
    }
}
