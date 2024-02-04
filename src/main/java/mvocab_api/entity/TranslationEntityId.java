package mvocab_api.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TranslationEntityId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "lang_id")
    private LangEntity lang;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordEntity word;
}
