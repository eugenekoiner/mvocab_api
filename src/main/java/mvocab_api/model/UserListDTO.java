package mvocab_api.model;

import lombok.Data;
import mvocab_api.entity.LangEntity;

import java.util.List;

@Data
public class UserListDTO {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private List<LangEntity> langs;
}