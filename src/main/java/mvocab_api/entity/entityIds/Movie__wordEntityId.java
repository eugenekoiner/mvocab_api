package mvocab_api.entity.entityIds;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;

import java.io.Serializable;

@Data
@Embeddable
public class Movie__wordEntityId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordEntity word;
}