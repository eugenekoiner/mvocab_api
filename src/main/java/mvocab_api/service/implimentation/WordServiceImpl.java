package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.Word;
import mvocab_api.model.WordListDTO;
import mvocab_api.repository.WordRepository;
import mvocab_api.service.WordService;
import mvocab_api.service.WordsResponse;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public WordEntity createWord(WordEntity wordEntity) {
        return wordRepository.save(wordEntity);
    }

    @Override
    public WordsResponse findAllWords(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WordEntity> words = wordRepository.findAll(pageable);
        List<WordListDTO> content = words.stream().map(WordListDTO::toModel).collect(Collectors.toList());
        WordsResponse wordsResponse = new WordsResponse();
        wordsResponse.setContent(content);
        wordsResponse.setPage(words.getNumber());
        wordsResponse.setSize(words.getSize());
        wordsResponse.setTotalElements(words.getTotalElements());
        wordsResponse.setTotalPages(words.getTotalPages());
        wordsResponse.setLast(words.isLast());
        return wordsResponse;
    }

    @Override
    public WordEntity findById(Integer id) throws DoesNotExistException {
        return wordRepository.findById(id).orElseThrow(() -> new DoesNotExistException("word"));
    }

    @Override
    public WordEntity updateWord(Integer id, WordEntity wordEntity) throws DoesNotExistException {
        try {
            if (wordRepository.updateWordById(id, wordEntity) < 1) {
                throw new DoesNotExistException("word");
            }
        } catch (DataIntegrityViolationException | DoesNotExistException e) {
            throw new DoesNotExistException("word");
        }
        return wordRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteWord(Integer id) throws DoesNotExistException {
        if (wordRepository.deleteWordById(id) < 1) {
            throw new DoesNotExistException("word");
        }
        return "removed";
    }
}
