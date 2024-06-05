package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.MovieById;
import mvocab_api.model.MovieList;
import mvocab_api.model.WordList;
import mvocab_api.repository.LangRepository;
import mvocab_api.repository.MovieRepository;
import mvocab_api.service.EntityMapper;
import mvocab_api.service.MovieService;
import mvocab_api.service.PaginationResponse;
import mvocab_api.service.PaginationResponseForOmdbSearch;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import subtitles_api.omdb.OmdbApiSteps;
import subtitles_api.omdb.dto.OmdbMovieListDTO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) throws Exception {
        try {
            return movieRepository.save(movieEntity);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public PaginationResponse<MovieList> findAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<MovieEntity> moviesPage = movieRepository.findAll(pageable);
        List<MovieList> content = moviesPage.stream().map(EntityMapper.INSTANCE::toMovieForList).collect(Collectors.toList());
        return new PaginationResponse<>(new PageImpl<>(content, pageable, moviesPage.getTotalElements()));
    }

    @Override
    public MovieEntity findMovieEntityById(Integer id) throws DoesNotExistException {
        return movieRepository.findById(id).orElseThrow(() -> new DoesNotExistException("movie"));
    }

    @Override
    public MovieById findMovieById(Integer id) throws DoesNotExistException {
        return EntityMapper.INSTANCE.toMovieById(movieRepository.findById(id).orElseThrow(() -> new DoesNotExistException("movie")));
    }

    @Override
    public MovieById findMovieByImdbId(String imdbId) throws Exception {
        MovieById movieById;
        try {
            MovieEntity movie = movieRepository.findMovieByImdbId(imdbId);
            if (movie == null) {
                movieById = EntityMapper.INSTANCE.omdbToMovieId(new OmdbApiSteps().getOmdbMovieByImdbId(imdbId));
                createMovie(EntityMapper.INSTANCE.toMovieEntity(movieById));
            } else {
                movieById = EntityMapper.INSTANCE.toMovieById(movie);
            }
            return movieById;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public PaginationResponseForOmdbSearch<MovieList> findMoviesByName(String name, int page) {
        OmdbApiSteps omdbPage = new OmdbApiSteps();
        Page<OmdbMovieListDTO> moviesPage = omdbPage.getOmdbMovieListByName(name, page);
        List<MovieList> content = moviesPage.stream().map(EntityMapper.INSTANCE::omdbToMovieList).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(page, content.size());
        return new PaginationResponseForOmdbSearch<>(new PageImpl<>(content, pageable, moviesPage.getTotalElements()), omdbPage.total);
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
    public List<WordList> findWordsEntitiesByMovieId(Integer id) throws DoesNotExistException {
        findMovieEntityById(id);
        List<WordList> content = movieRepository.findWordsByMovieId(id).stream().map(EntityMapper.INSTANCE::toWordForList).toList();
        return content;
    }

    @Override
    public List<String> findWordsByMovieId(Integer id) throws DoesNotExistException {
        findMovieEntityById(id);
        List<WordEntity> wordLists = movieRepository.findWordsByMovieId(id);
        return wordLists.stream().map(WordEntity::getWord).toList();
    }

}
