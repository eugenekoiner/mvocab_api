package mvocab_api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import mvocab_api.entity.entityIds.User__wordEntityId;

@Data
@Entity
@Table(name = "user__word")
public class User__wordEntity {
    @EmbeddedId
    private User__wordEntityId id;
}