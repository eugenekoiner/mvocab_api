package mvocab_api.controller;


import lombok.AllArgsConstructor;
import mvocab_api.service.ResponseMessage;
import mvocab_api.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/words")
@AllArgsConstructor

public class WordController {
    private final WordService wordService;

    @GetMapping
    public ResponseEntity<Object> findAllWords(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(wordService.findAllWords(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }


}
