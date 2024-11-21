package translator;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class TranslationsDTO {
    private Map<String, TranslationDTO> translations = new LinkedHashMap<>();
    private String lessCommon;
}