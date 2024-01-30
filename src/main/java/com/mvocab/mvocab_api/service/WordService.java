package com.mvocab.mvocab_api.service;

import com.mvocab.mvocab_api.entity.WordEntity;

import java.util.List;
import java.util.Optional;

public interface WordService {
    List<WordEntity> findAllWords();
    Optional<WordEntity> findById(Integer id);
    WordEntity updateWord (Integer id, WordEntity wordEntity);
    String deleteWord (Integer id);


}
