package mvocab_api.model;

import lombok.Data;

@Data
public class MovieList {
    //private Integer id;
    private String imdb_id;
    private String name;
    private String type;
    private String year;
    private String img;
}