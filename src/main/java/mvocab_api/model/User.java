package mvocab_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private List<LangEntity> userLangs;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setEmail(entity.getEmail());
        model.setPhone(entity.getPhone());
        model.setUserLangs(entity.getUserLangs());
        return model;
    }
}