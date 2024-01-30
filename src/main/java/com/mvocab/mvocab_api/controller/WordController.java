package com.mvocab.mvocab_api.controller;


import com.mvocab.mvocab_api.entity.WordEntity;
import com.mvocab.mvocab_api.service.WordService;
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
