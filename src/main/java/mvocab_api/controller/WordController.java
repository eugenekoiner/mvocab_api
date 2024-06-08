package mvocab_api.controller;


import lombok.AllArgsConstructor;
import mvocab_api.entity.TranslationEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.model.WordById;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.EntityMapper;
import mvocab_api.service.TranslationService;
import mvocab_api.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import translator.DeeplTranslator;


@RestController
@RequestMapping("/api/words")
@AllArgsConstructor

public class WordController {
    private final WordService wordService;
    private final TranslationService translationService;

    // создать новое слово вместе с переводом
    @PostMapping
    public ResponseEntity<?> createWord(@RequestBody WordById wordById) {
        try {
            WordEntity wordEntity = new WordEntity();

            //wordEntity.setTranslation("sdf");



            return null; //new ResponseEntity<>(wordById.setTranslation(wordById.getTranslation()), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("message: " + e.getMessage());
        }
    }

    // получить список всех слов без переводов (с параметрами и пагинацией)
    @GetMapping
    public ResponseEntity<Object> findAllWords(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(wordService.findAllWords(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить данные о конкретном слове вместе с переводом
    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            WordEntity wordEntity = wordService.findById(id);
            TranslationEntity translationEntity = translationService.findTranslationByLangIdAndWordId(wordEntity.getLang_id(),id);
            if (translationEntity == null) {
                translationService.insertTranslation(new DeeplTranslator().getDeeplTranslation(wordEntity.getWord()), wordEntity.getId());
            }
            return ResponseMessage.responseMessage(EntityMapper.INSTANCE.toWordById(wordEntity));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // изменить данные конкретного слова или его перевод
    @PutMapping("{id}")
    public ResponseEntity<Object> updateWord(@PathVariable Integer id, @RequestBody WordEntity wordEntity) {
        try {
            return ResponseMessage.responseMessage(wordService.updateWord(id, wordEntity));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить конкретное слово
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteWord(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("message", wordService.deleteWord(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }
}