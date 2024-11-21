package mvocab_api.service;

import lombok.Data;
import mvocab_api.entity.WordEntity;
import translator.TranslationsDTO;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class WordByIdResponse {
    private Integer id;
    private String word;
    private Integer lang_id;
    private Map<String, Object> translations = new LinkedHashMap<>();

    public WordByIdResponse(WordEntity wordEntity, TranslationsDTO translationsDTO) {
        this.id = wordEntity.getId();
        this.word = wordEntity.getWord();
        this.lang_id = wordEntity.getLang_id();
        this.translations.putAll(translationsDTO.getTranslations());
        this.translations.put("lessCommon", translationsDTO.getLessCommon());
    }
}