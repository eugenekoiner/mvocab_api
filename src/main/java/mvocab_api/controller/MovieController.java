package mvocab_api.controller;


import lombok.AllArgsConstructor;
import mvocab_api.entity.MovieEntity;
import mvocab_api.model.MovieById;
import mvocab_api.service.MovieService;
import mvocab_api.service.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor

public class MovieController {
    private final MovieService movieService;

    // создать новый фильм
    @PostMapping
    public ResponseEntity<Object> createMovie(@RequestBody MovieEntity movieEntity) {
        try {
            MovieEntity movie = movieService.createMovie(movieEntity);
            return ResponseMessage.responseMessage(movie);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // искать фильм (с параметрами и пагинацией)
    @GetMapping
    public ResponseEntity<Object> findMovieOrSeries(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                    @RequestParam(value = "search", required = false) String search,
                                                    @RequestParam(value = "imdb_id", required = false) String imdbId,
                                                    @RequestParam(value = "season", required = false) String season,
                                                    @RequestParam(value = "episode", required = false) String episode) {
        try {
            if (imdbId != null) {
                if (season != null && episode != null) {
                    return new ResponseEntity<>(movieService.findSeriesByImdbId(imdbId, season, episode), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(movieService.findMovieByImdbId(imdbId), HttpStatus.OK);
                }
            } else if (search != null) {
                return ResponseMessage.responseMessage(movieService.findMoviesByName(search, page));
            } else {
                //todo: сюда добавлю поиск по жанрам, типу, языкам, режиссерам, актерам и тд. Надо погуглить как делается множественный фильтр
                return new ResponseEntity<>(movieService.findAllMovies(page, size), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить данные о конкретном фильме по айди
    @GetMapping("{id}")
    public ResponseEntity<Object> findMovieById(@PathVariable Integer id) {
        try {
            MovieById movieById = movieService.findMovieById(id);
            return ResponseMessage.responseMessage(movieById);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // изменить данные о конкретном фильме
    @PutMapping("{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable Integer id, @RequestBody MovieEntity movieEntity) {
        try {
            return ResponseMessage.responseMessage(movieService.updateMovie(id, movieEntity));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // удалить фильм
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("message", movieService.deleteMovie(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить список слов в конкретном фильме
    @GetMapping("{id}/words")
    public ResponseEntity<Object> findWordsByMovieId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("words", movieService.findWordsByMovieId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }
}



