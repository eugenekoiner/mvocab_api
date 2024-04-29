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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final LangRepository langRepository;

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public PaginationResponse<MovieList> findAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieEntity> moviesPage = movieRepository.findAll(pageable);
        List<MovieList> content = moviesPage.stream().map(EntityMapper.INSTANCE::toMovieForList).collect(Collectors.toList());
        return new PaginationResponse<>(new PageImpl<>(content, pageable, moviesPage.getTotalElements()));
    }

    @Override
    public MovieEntity findMovieEntityById(Integer id) throws DoesNotExistException {
        return movieRepository.findById(id).orElseThrow(() -> new DoesNotExistException("movie"));
    }

    public MovieById findMovieById(Integer id) throws DoesNotExistException {
        MovieById movieById = EntityMapper.INSTANCE.toMovieById(movieRepository.findById(id).orElseThrow(() -> new DoesNotExistException("movie")));
        movieById.setLangs(findLangsByMovieId(id));
        return movieById;
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

    @Override
    public List<String> findLangsByMovieId(Integer id) throws DoesNotExistException {
        LinkedHashSet<String> langIdList = new LinkedHashSet<>();
        for (WordList word : findWordsEntitiesByMovieId(id)) {
            langIdList.add(langRepository.findLangByWordId(word.getId()).getName());
        }
        return langIdList.stream().toList();
    }
}
