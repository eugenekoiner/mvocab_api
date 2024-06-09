package translator;

import lombok.Data;

@Data
public class TranslationDTO {
    private String translation;
    private String partOfSpeech;
    private String example;
}