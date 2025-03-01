package subtitles_api.omdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class OmdbMovieIdDTO {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Season")
    private String season;
    @JsonProperty("Episode")
    private String episode;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("Ratings")
    private List<RatingsDTO> ratings;
    @JsonProperty("imdbRating")
    private String imdb_rating;
    @JsonProperty("imdbID")
    private String imdb_id;
    @JsonProperty("seriesID")
    private String series_id;
    @JsonProperty("totalSeasons")
    private String total_seasons;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Poster")
    private String poster;
}
