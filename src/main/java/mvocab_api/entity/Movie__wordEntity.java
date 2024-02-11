package mvocab_api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import mvocab_api.entity.entityIds.Movie__wordEntityId;
import mvocab_api.entity.entityIds.User__wordEntityId;

@Data
@Entity
@Table(name = "movie__word")
public class Movie__wordEntity {
    @EmbeddedId
    private Movie__wordEntityId id;
}