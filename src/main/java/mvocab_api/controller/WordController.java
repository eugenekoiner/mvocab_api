package mvocab_api.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mvocab_api.entity.TranslationEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.TranslationService;
import mvocab_api.service.WordByIdResponse;
import mvocab_api.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import translator.DeeplTranslator;
import translator.TranslationsDTO;

import java.io.IOException;


@RestController
@RequestMapping("/api/words")
@AllArgsConstructor

public class WordController {
    private final WordService wordService;
    private final TranslationService translationService;

    // создать новое слово вместе с переводом
    @PostMapping
    public ResponseEntity<?> createWord() {
        try {
            return ResponseEntity.badRequest().body("message: " + "эта фунция недоступна");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("message: " + e.getMessage());
        }
    }

    // получить список всех слов без переводов (с параметрами и пагинацией)
    @GetMapping
    public ResponseEntity<?> findAllWords(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
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
            TranslationEntity translationEntity = translationService.findTranslationByLangIdAndWordId(wordEntity.getLang_id(), id);
            ObjectMapper objectMapper = new ObjectMapper();
            TranslationsDTO translationsDTO;
            if (translationEntity == null || translation1IsMissing(translationEntity.getTranslation())) {
                translationsDTO = new DeeplTranslator().getDeeplTranslation(wordEntity.getWord());
                String jsonTranslation = objectMapper.writeValueAsString(translationsDTO);
                if (translationEntity == null) {
                    translationService.insertTranslation(jsonTranslation, id);
                } else {
                    translationService.updateTranslationByLangIdAndWordId(jsonTranslation, wordEntity.getLang_id(), id);
                }
                translationEntity = translationService.findTranslationByLangIdAndWordId(wordEntity.getLang_id(), id);
            }
            translationsDTO = objectMapper.readValue(translationEntity.getTranslation(), TranslationsDTO.class);
            WordByIdResponse response = new WordByIdResponse(wordEntity, translationsDTO);
            return ResponseMessage.responseMessage(response);
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

    private boolean translation1IsMissing(String jsonTranslation) {
        try {
            JsonNode translation1Node = new ObjectMapper().readTree(jsonTranslation)
                    .path("translations").path("translation1").path("translation");
            return translation1Node.isMissingNode() || translation1Node.asText().isEmpty() || translation1Node.isNull();
        } catch (IOException e) {
            return true;
        }
    }
}