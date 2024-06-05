package mvocab_api.repository;

import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE MovieEntity m SET m.name = :#{#movie.name}, m.description = :#{#movie.description}, m.langs = :#{#movie.langs}, m.writer = :#{#movie.writer}, m.released = :#{#movie.released}, m.rated = :#{#movie.rated}, m.imdb_rating = :#{#movie.imdb_rating}, m.genre = :#{#movie.genre}, m.director = :#{#movie.director}, m.country = :#{#movie.country}, m.awards = :#{#movie.awards}, m.actors = :#{#movie.actors}, m.year = :#{#movie.year}, m.type = :#{#movie.type}, m.imdbid = :#{#movie.imdbid}, m.img = :#{#movie.img}, m.trailer = :#{#movie.trailer},m.ratings = :#{#movie.ratings} WHERE m.id = :id")
    int updateMovieById(@Param("id") Integer id, @Param("movie") MovieEntity movieEntity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM MovieEntity m where m.id = ?1")
    int deleteMovieById(Integer id);

    @Modifying
    @Query("SELECT wm.id.word FROM Movie__wordEntity wm WHERE wm.id.movie.id = :movieId")
    List<WordEntity> findWordsByMovieId(@Param("movieId") Integer movieId);

    @Query(value = "SELECT m FROM MovieEntity m where m.imdbid = :imdbId")
    MovieEntity findMovieByImdbId(String imdbId);

}
