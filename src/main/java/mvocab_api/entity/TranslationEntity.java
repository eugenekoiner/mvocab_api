package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "translation")
public class TranslationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String translation;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "word_id", referencedColumnName = "id")
    private WordEntity word;

    @Override
    public String toString() {
        return "TranslationEntity{" +
                "id=" + id +
                ", translation='" + translation + '\'' +
                '}';
    }
}
