package mvocab_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "word")
public class WordEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String word;
    private String transcription;
    private String audio;
    private Integer lang_id;
    private String translation;

    @ManyToMany(mappedBy = "userWords")
    @JsonIgnore
    private List<UserEntity> users;

    @ManyToOne
    private MovieEntity movie;
}