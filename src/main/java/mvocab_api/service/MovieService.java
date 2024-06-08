package mvocab_api.service;

import mvocab_api.entity.MovieEntity;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.MovieById;
import mvocab_api.model.MovieList;
import mvocab_api.model.WordList;

import java.sql.SQLException;
import java.util.List;

public interface MovieService {
    MovieEntity createMovie(MovieEntity movieEntity) throws Exception;
    PaginationResponse findAllMovies(int page, int size);
    MovieEntity findMovieEntityById(Integer id) throws DoesNotExistException;
    MovieById findMovieById(Integer id) throws DoesNotExistException;
    PaginationResponseForOmdbSearch<MovieList> findMoviesByName (String name, int page) throws DoesNotExistException;
    MovieEntity updateMovie(Integer id, MovieEntity movieEntity) throws DoesNotExistException;
    String deleteMovie(Integer id) throws DoesNotExistException;
    List<WordList> findWordsEntitiesByMovieId(Integer id) throws DoesNotExistException;
    List<String> findWordsByMovieId(Integer id) throws Exception;
    MovieById findMovieByImdbId(String imdbId) throws Exception;
}
