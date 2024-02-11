package mvocab_api.controller;


import lombok.AllArgsConstructor;
import mvocab_api.entity.WordEntity;
import mvocab_api.model.Word;
import mvocab_api.model.WordByIdDTO;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/words")
@AllArgsConstructor

public class WordController {
    private final WordService wordService;

    // создать новое слово вместе с переводом
    @PostMapping
    public ResponseEntity<Object> createWord(@RequestBody WordEntity wordEntity) {
        try {
            WordEntity word = wordService.createWord(wordEntity);
            return ResponseMessage.responseMessage(word);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить список всех слов с переводами (с параметрами и пагинацией)
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
            return ResponseMessage.responseMessage(WordByIdDTO.toModel(wordService.findById(id)));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // изменить данные конкретного слова иили его перевод
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