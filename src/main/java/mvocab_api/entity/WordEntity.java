package mvocab_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "word")
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String word;
    private String transcription;
    private String audio;
    //todo:  надо сюда запихать стринг чтоб работало сразу с текстом а не со списком айдишников
    private Integer lang_id;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "word")
    private TranslationEntity translation;

    @ManyToMany(mappedBy = "userWords")
    @JsonIgnore
    private List<UserEntity> users;

    @ManyToOne
    private MovieEntity movie;
}