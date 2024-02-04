package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.WordEntity;
import mvocab_api.model.Word;
import mvocab_api.repository.WordRepository;
import mvocab_api.service.WordService;
import mvocab_api.service.WordsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    //todo: понять как сделать пагинацию
    @Override
    public WordsResponse findAllWords(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WordEntity> words = wordRepository.findAll(pageable);
        List<Word> content = words.stream().map(Word::toModel).collect(Collectors.toList());
        WordsResponse wordsResponse = new WordsResponse();
        wordsResponse.setContent(content);
        wordsResponse.setPage(words.getNumber());
        wordsResponse.setSize(words.getSize());
        wordsResponse.setTotalElements(words.getTotalElements());
        wordsResponse.setTotalPages(words.getTotalPages());
        wordsResponse.setLast(words.isLast());

        return wordsResponse;
        //return wordRepository.findAll();
    }

    @Override
    public Optional<WordEntity> findById(Integer id) {
        return wordRepository.findById(id);
    }

    @Override
    public WordEntity updateWord(Integer id, WordEntity wordEntity) {
        return null;
    }

    @Override
    public String deleteWord(Integer id) {
        return null;
    }
}
