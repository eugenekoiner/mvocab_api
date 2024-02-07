package mvocab_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import mvocab_api.entity.WordEntity;

@Data
public class Word {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String word;
    //private String transcription;
    //private String audio;
    private Integer lang_id;
    private String translation;

    public static Word toModel(WordEntity entity) {
        Word model = new Word();
        model.setId(entity.getId());
        model.setWord(entity.getWord());
        //model.setTranscription(entity.getTranscription());
        //model.setAudio(entity.getAudio());
        model.setLang_id(entity.getLang_id());
        model.setTranslation(entity.getTranslation());
        return model;
    }
}