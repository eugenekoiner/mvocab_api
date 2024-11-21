package mvocab_api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import mvocab_api.entity.entityIds.User__roleEntityId;

@Data
@Entity
@Table(name = "user__role")
public class User__roleEntity {
    @EmbeddedId
    private User__roleEntityId id;
}