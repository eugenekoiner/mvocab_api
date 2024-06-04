package mvocab_api.service;

import mvocab_api.entity.MovieEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.MovieById;
import mvocab_api.model.MovieList;
import mvocab_api.model.WordList;

import java.util.List;

public interface MovieService {
    PaginationResponse findAllMovies(int page, int size);

    MovieEntity findMovieEntityById(Integer id) throws DoesNotExistException;
    MovieById findMovieById(Integer id) throws DoesNotExistException;

    PaginationResponse<MovieList> findMoviesByName (String name, int page) throws DoesNotExistException;

    MovieEntity updateMovie(Integer id, MovieEntity movieEntity) throws DoesNotExistException;

    String deleteMovie(Integer id) throws DoesNotExistException;

    MovieEntity createMovie(MovieEntity movieEntity) throws AlreadyExistException;

    List<WordList> findWordsEntitiesByMovieId(Integer id) throws DoesNotExistException;
    List<String> findWordsByMovieId(Integer id) throws DoesNotExistException;
    List<String> findLangsByMovieId(Integer id) throws DoesNotExistException;
}
