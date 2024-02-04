package mvocab_api.service;

import mvocab_api.entity.WordEntity;

import java.util.Optional;

public interface WordService {
    WordsResponse findAllWords(int page, int size);

    Optional<WordEntity> findById(Integer id);

    WordEntity updateWord(Integer id, WordEntity wordEntity);

    String deleteWord(Integer id);


}
