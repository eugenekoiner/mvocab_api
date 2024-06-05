package mvocab_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import subtitles_api.omdb.dto.RatingsDTO;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imdbid;
    @Column(nullable = false)
    private String name;
    private String type;
    private String year;
    private String rated;
    private String released;
    private String genre;
    private String director;
    private String writer;
    private String actros;
    private String country;
    private String awards;
    private String ratings;
    private String imdbRating;
    private String description;
    private String img;
    private String trailer;

    @ManyToMany(mappedBy = "userMovies")
    @JsonIgnore
    private List<UserEntity> movieUsers;

    @OneToMany
    @JoinTable(name = "movie__word", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "word_id")})
    @JsonIgnore
    private List<WordEntity> movieWords;
}