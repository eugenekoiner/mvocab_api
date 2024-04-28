package mvocab_api.service;

import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;

public interface WordService {
    PaginationResponse findAllWords(int page, int size);

    WordEntity findById(Integer id) throws DoesNotExistException;

    WordEntity updateWord(Integer id, WordEntity wordEntity) throws DoesNotExistException;

    String deleteWord(Integer id) throws DoesNotExistException;


    WordEntity createWord(WordEntity wordEntity) throws AlreadyExistException;
}
