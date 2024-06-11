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
    private String imdb_id;
    @Column(nullable = false)
    private String name;
    private String type;
    private String year;
    private String rated;
    private String released;
    private String genre;
    @Column(columnDefinition = "TEXT")
    private String director;
    @Column(columnDefinition = "TEXT")
    private String writer;
    @Column(columnDefinition = "TEXT")
    private String actors;
    private String country;
    @Column(columnDefinition = "TEXT")
    private String awards;
    @Column(columnDefinition = "TEXT")
    private String ratings;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String img;
    @Column(columnDefinition = "TEXT")
    private String trailer;
    private String imdb_rating;
    private String langs;
    private Integer srt_id;

    @ManyToMany(mappedBy = "userMovies")
    @JsonIgnore
    private List<UserEntity> movieUsers;

    @OneToMany
    @JoinTable(name = "movie__word", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "word_id")})
    @JsonIgnore
    private List<WordEntity> movieWords;
}