package mvocab_api.model;

import lombok.Data;

@Data
public class MovieListDTO {
    private Integer id;
    private String name;
    private Integer rating;
}