package mvocab_api.service;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MoviesResponse findAllMovies(int page, int size);

    MovieEntity findById(Integer id) throws DoesNotExistException;

    MovieEntity updateMovie(Integer id, MovieEntity movieEntity) throws DoesNotExistException;

    String deleteMovie(Integer id) throws DoesNotExistException;

    MovieEntity createMovie(MovieEntity movieEntity) throws AlreadyExistException;

    List<WordEntity> findWordsByMovieId(Integer id) throws DoesNotExistException;
}
