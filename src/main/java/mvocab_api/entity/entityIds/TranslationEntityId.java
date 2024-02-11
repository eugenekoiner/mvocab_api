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

    private Integer langId;
    private Integer wordId;
}
