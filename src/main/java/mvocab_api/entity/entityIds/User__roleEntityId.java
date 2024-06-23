package mvocab_api.entity.entityIds;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mvocab_api.entity.RoleEntity;
import mvocab_api.entity.UserEntity;

import java.io.Serializable;

@Data
@Embeddable
public class User__roleEntityId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}