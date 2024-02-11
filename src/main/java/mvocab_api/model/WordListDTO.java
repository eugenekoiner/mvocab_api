package mvocab_api.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import mvocab_api.entity.WordEntity;

@Data
public class WordListDTO {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String word;
    //private String transcription;
    //private String audio;
    private Integer lang_id;
    @JsonRawValue

    public static WordListDTO toModel(WordEntity entity) {
        WordListDTO model = new WordListDTO();
        model.setId(entity.getId());
        model.setWord(entity.getWord());
        //model.setTranscription(entity.getTranscription());
        //model.setAudio(entity.getAudio());
        model.setLang_id(entity.getLang_id());
        return model;
    }
}
