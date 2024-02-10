package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.LangEntity;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.Movie;
import mvocab_api.model.User;
import mvocab_api.repository.MovieRepository;
import mvocab_api.service.MovieService;
import mvocab_api.service.MoviesResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) {
            return movieRepository.save(movieEntity);
    }

    @Override
    public MoviesResponse findAllMovies(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MovieEntity> movies = movieRepository.findAll(pageable);
        List<Movie> content = movies.stream().map(Movie::toModel).collect(Collectors.toList());
        MoviesResponse moviesResponse = new MoviesResponse();
        moviesResponse.setContent(content);
        moviesResponse.setPage(movies.getNumber());
        moviesResponse.setSize(movies.getSize());
        moviesResponse.setTotalElements(movies.getTotalElements());
        moviesResponse.setTotalPages(movies.getTotalPages());
        moviesResponse.setLast(movies.isLast());
        return moviesResponse;
    }

    @Override
    public MovieEntity findById(Integer id) throws DoesNotExistException {
        return movieRepository.findById(id).orElseThrow(() -> new DoesNotExistException("movie"));
    }

    @Override
    public MovieEntity updateMovie(Integer id, MovieEntity movieEntity) throws DoesNotExistException {
        try {
            if (movieRepository.updateMovieById(id, movieEntity) < 1) {
                throw new DoesNotExistException("movie");
            }
        } catch (DataIntegrityViolationException | DoesNotExistException e) {
            throw new DoesNotExistException("movie");
        }
        return movieRepository.findById(id).orElse(null);
    }


    @Override
    public String deleteMovie(Integer id) throws DoesNotExistException {
        if (movieRepository.deleteMovieById(id) < 1) {
            throw new DoesNotExistException("movie");
        }
        return "removed";
    }

    @Override
    public List<WordEntity> findWordsByMovieId(Integer id) throws DoesNotExistException {
        findById(id);
        return movieRepository.findWordsByMovieId(id);
    }
}
