package com.mvocab.mvocab_api.service.implimentation;

import com.mvocab.mvocab_api.model.Word;
import com.mvocab.mvocab_api.repository.WordRepository;
import com.mvocab.mvocab_api.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    //todo: понять как сделать пагинацию
    @Override
    public List<Word> findAllWords() {
        return wordRepository.findAll();
    }

    @Override
    public Optional<Word> findById(Integer id) {
        return wordRepository.findById(id);
    }

    @Override
    public Word updateWord(Integer id, Word word) {
        return null;
    }

    @Override
    public String deleteWord(Integer id) {
        return null;
    }
}
