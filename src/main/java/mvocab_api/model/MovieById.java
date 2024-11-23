package mvocab_api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import subtitles_api.omdb.dto.RatingsDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieById {
    private Integer id;
    private String imdb_id;
    private String series_id;
    private String season;
    private String episode;
    private String total_seasons;
    private String name;
    private String type;
    private String year;
    private String rated;
    private String released;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String country;
    private String awards;
    private List<RatingsDTO> ratings;
    private String imdb_rating;
    private String description;
    private String img;
    private String trailer;
    private String langs;
}
