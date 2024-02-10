package mvocab_api.service;

import lombok.Data;
import mvocab_api.model.Movie;

import java.util.List;

@Data
public class MoviesResponse {
    private List<Movie> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}