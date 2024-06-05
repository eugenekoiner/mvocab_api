package mvocab_api.entity.entityIds;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;

import java.io.Serializable;

@Data
@Embeddable
public class User__wordEntityId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordEntity word;
}