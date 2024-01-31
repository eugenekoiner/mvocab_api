package mvocab_api.controller;


import mvocab_api.entity.WordEntity;
import mvocab_api.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/words")
@AllArgsConstructor

public class WordController {
    private final WordService wordService;

    @GetMapping
    public List<WordEntity> findAllWords () {
        return wordService.findAllWords();
    }



}
