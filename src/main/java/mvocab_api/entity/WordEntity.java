package mvocab_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import translator.TranslationDTO;

import java.util.List;

@Data
@Entity
@DynamicInsert
@Table(name = "word")
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String word;
    private String transcription;
    private String audio;
    private Integer lang_id;

    @ToString.Exclude
    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "word")
    private TranslationEntity translation;

    @ManyToMany(mappedBy = "userWords")
    @JsonIgnore
    private List<UserEntity> users;

    @ManyToOne
    private MovieEntity movie;

    @Transient
    private TranslationDTO translationDTO;

    @Override
    public String toString() {
        return "WordEntity{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", transcription='" + transcription + '\'' +
                ", audio='" + audio + '\'' +
                ", lang_id=" + lang_id +
                '}';
    }
}