package mvocab_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String img;
    private Integer rating; 
    private String trailer;

    @ManyToMany(mappedBy = "userMovies")
    @JsonIgnore
    private List<UserEntity> movieUsers;

    @OneToMany
    @JoinTable(name = "movie__word", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "word_id")})
    @JsonIgnore
    private List<WordEntity> movieWords;
}