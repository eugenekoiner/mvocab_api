package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Column(unique = true)
    private String phone;
    private String favorites;

    @ManyToMany
    @JoinTable(name = "user__lang", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "lang_id")})
    private List<LangEntity> userLangs;

    @ManyToMany
    @JoinTable(name = "user__word", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "word_id")})
    private List<WordEntity> userWords;
}
