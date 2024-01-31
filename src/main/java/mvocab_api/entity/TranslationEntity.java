package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "translation")
public class TranslationEntity {
    @Id
    private int id;
    private String translation;
}
