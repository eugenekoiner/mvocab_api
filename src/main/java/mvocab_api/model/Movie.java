package mvocab_api.model;

import lombok.Data;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;

import java.util.List;

@Data
public class Movie {
    private Integer id;
    private String name;
    private Integer rating;

    public static Movie toModel(MovieEntity entity) {
        Movie model = new Movie();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
}