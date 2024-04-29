package mvocab_api.model;

import lombok.Data;

import java.util.List;

@Data
public class MovieById {
    private Integer id;
    private String name;
    private String description;
    private String img;
    private Integer rating;
    private String trailer;
    private List<String> langs;
    private List<String> movieWords;
}
