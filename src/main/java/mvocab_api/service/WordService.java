package mvocab_api.service;

import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;

import java.util.Optional;

public interface WordService {
    WordsResponse findAllWords(int page, int size);

    Optional<WordEntity> findById(Integer id);

    WordEntity updateWord(Integer id, WordEntity wordEntity) throws DoesNotExistException;

    String deleteWord(Integer id) throws DoesNotExistException;


    WordEntity createWord(WordEntity wordEntity) throws AlreadyExistException;
}
