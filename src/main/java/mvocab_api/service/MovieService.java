package mvocab_api.service;

import mvocab_api.entity.MovieEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.WordList;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    PaginationResponse findAllMovies(int page, int size);

    MovieEntity findById(Integer id) throws DoesNotExistException;

    MovieEntity updateMovie(Integer id, MovieEntity movieEntity) throws DoesNotExistException;

    String deleteMovie(Integer id) throws DoesNotExistException;

    MovieEntity createMovie(MovieEntity movieEntity) throws AlreadyExistException;

    List<WordList> findWordsByMovieId(Integer id) throws DoesNotExistException;
    List<Optional> findLangsByMovieId(Integer id) throws DoesNotExistException;
}
