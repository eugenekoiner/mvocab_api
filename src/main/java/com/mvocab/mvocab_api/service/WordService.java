package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.model.Word;

import java.util.List;
import java.util.Optional;

public interface WordService {
    List<Word> findAllWords();
    Optional<Word> findById(Integer id);
    Word updateWord (Integer id, Word word);
    String deleteWord (Integer id);


}
