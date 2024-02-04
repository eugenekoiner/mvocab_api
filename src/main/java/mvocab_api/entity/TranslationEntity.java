package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.InheritanceType.JOINED;

@Data
@Entity
@Table(name = "translation")
public class TranslationEntity {
    @EmbeddedId
    private TranslationEntityId id;

    @Column(name = "translation")
    private String translation;
}
