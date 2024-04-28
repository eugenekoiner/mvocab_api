package mvocab_api.service;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;

import java.util.List;

public interface UserService {

    PaginationResponse findAllUsers(int page, int size);

    UserEntity registerUser(UserEntity userEntity) throws AlreadyExistException;

    UserEntity findById(Integer id) throws DoesNotExistException;

    UserEntity updateUser(Integer id, UserEntity userEntity) throws DoesNotExistException, AlreadyExistException;

    String deleteUser(Integer id) throws DoesNotExistException;

    List<LangEntity> findLangsByUserId(Integer id) throws DoesNotExistException;

    Object addLangByUserId(Integer id, Integer langId) throws Exception;

    Object deleteLangByUserId(Integer id, Integer langId) throws DoesNotExistException;

    Object findWordsByUserId(Integer id) throws DoesNotExistException;

    Object addWordByUserId(Integer id, Integer wordId) throws Exception;

    String deleteWordByUserId(Integer id, Integer langId) throws DoesNotExistException;
}
