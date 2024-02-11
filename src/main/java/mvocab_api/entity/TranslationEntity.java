package mvocab_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.Data;
import mvocab_api.entity.entityIds.TranslationEntityId;

@Data
@Entity
@Table(name = "translation")
public class TranslationEntity {
    @EmbeddedId
    private TranslationEntityId id;


    @Column(name = "translation")
    private String translation;

    @MapsId("wordId") // Сообщает Hibernate использовать wordId как FK
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id")
    private WordEntity word;
}
