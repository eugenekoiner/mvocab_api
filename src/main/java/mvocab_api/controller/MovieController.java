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

    // получить полный список фильмов с данными (с параметрами и пагинацией)
    @GetMapping
    public ResponseEntity<Object> findAllMovies(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        try {
            return new ResponseEntity<>(movieService.findAllMovies(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить данные о конкретном фильме вместе с его языками по айди
    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            MovieById movieById = movieService.findMovieById(id);
            return ResponseMessage.responseMessage(movieById);
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }

    // получить данные о конкретном фильме вместе с его языками по названию
    @GetMapping("{search}")
    public ResponseEntity<Object> findByName(@PathVariable String name) {
        try {
            MovieById movieById = movieService.findMovieByName(name);
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

    // получить список языков в конкретном фильме
    @GetMapping("{id}/langs")
    public ResponseEntity<Object> findLangsByMovieId(@PathVariable Integer id) {
        try {
            return ResponseMessage.responseMessage("langs", movieService.findLangsByMovieId(id));
        } catch (Exception e) {
            return ResponseMessage.responseMessage("message", e.getMessage());
        }
    }
}



