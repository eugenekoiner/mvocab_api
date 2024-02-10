package mvocab_api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import mvocab_api.entity.entityIds.User__movieEntityId;

@Data
@Entity
@Table(name = "user__movie")
public class User__movieEntity {
    @EmbeddedId
    private User__movieEntityId id;
}