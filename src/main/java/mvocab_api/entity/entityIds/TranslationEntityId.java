package mvocab_api.entity.entityIds;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mvocab_api.entity.LangEntity;
import mvocab_api.entity.WordEntity;

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
