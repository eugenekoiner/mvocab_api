package mvocab_api.model;

import lombok.Data;
import subtitles_api.omdb.dto.RatingsDTO;

import java.util.List;

@Data
public class MovieById {
    private Integer id;
    private String imdbid;
    private String name;
    private String type;
    private String year;
    private String rated;
    private String released;
    private String genre;
    private String director;
    private String writer;
    private String actros;
    private String country;
    private String awards;
    private List<RatingsDTO> ratings;
    private String imdbRating;
    private String description;
    private String img;
    private String trailer;
    private String langs;
    private List<String> words;
}
