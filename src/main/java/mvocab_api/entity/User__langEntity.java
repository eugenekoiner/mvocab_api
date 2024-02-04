package mvocab_api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import mvocab_api.entity.entityIds.User__langEntityId;

@Data
@Entity
@Table(name = "user__lang")
public class User__langEntity {
    @EmbeddedId
    private User__langEntityId id;
}