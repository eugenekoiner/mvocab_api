package mvocab_api.repository;

import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE MovieEntity m SET m.name = :#{#movie.name}, m.description = :#{#movie.description}, m.langs = :#{#movie.langs}, m.writer = :#{#movie.writer}, m.released = :#{#movie.released}, m.rated = :#{#movie.rated}, m.imdb_rating = :#{#movie.imdb_rating}, m.genre = :#{#movie.genre}, m.director = :#{#movie.director}, m.country = :#{#movie.country}, m.awards = :#{#movie.awards}, m.actors = :#{#movie.actors}, m.year = :#{#movie.year}, m.type = :#{#movie.type}, m.imdb_id = :#{#movie.imdb_id}, m.img = :#{#movie.img}, m.trailer = :#{#movie.trailer}, m.ratings = :#{#movie.ratings}, m.srt_id = :#{#movie.srt_id}, m.series_id = :#{#movie.series_id}, m.season = :#{#movie.season}, m.episode = :#{#movie.episode}, m.total_seasons = :#{#movie.total_seasons} WHERE m.id = :id")
    int updateMovieById(@Param("id") Integer id, @Param("movie") MovieEntity movieEntity);

    @Transactional
    @Modifying
    @Query("DELETE FROM MovieEntity m WHERE m.id = ?1")
    int deleteMovieById(Integer id);

    @Query("SELECT w FROM Movie__wordEntity wm JOIN wm.id.word w LEFT JOIN FETCH w.translation WHERE wm.id.movie.id = :movieId")
    List<WordEntity> findWordsByMovieId(@Param("movieId") Integer movieId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO movie__word (movie_id, word_id) " +
            "SELECT :movieId, w.id " +
            "FROM word w " +
            "WHERE w.id IN (:wordIds)", nativeQuery = true)
    int addWordsByMovieId(@Param("wordIds") List<Integer> wordIds, @Param("movieId") Integer movieId);

    @Query("SELECT m FROM MovieEntity m WHERE m.id = :id")
    MovieEntity findMovieById(@Param("id") Integer id);

    @Query("SELECT m FROM MovieEntity m WHERE m.imdb_id = :imdb_id")
    MovieEntity findMovieByImdbId(@Param("imdb_id") String imdb_id);

    @Query("SELECT m FROM MovieEntity m WHERE m.series_id = :imdb_id AND m.type = 'episode' AND m.season = :season AND m.episode = :episode")
    MovieEntity findSeriesByImdbId(@Param("imdb_id") String imdb_id, @Param("season") String season, @Param("episode") String episode);

}
