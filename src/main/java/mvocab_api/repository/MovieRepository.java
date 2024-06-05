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
    @Query(value = "UPDATE MovieEntity m SET m.name = :#{#movie.name}, m.description = :#{#movie.description}, m.rating = :#{#movie.rating} WHERE m.id = :id")
    int updateMovieById(@Param("id") Integer id, @Param("movie") MovieEntity movieEntity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM MovieEntity f where f.id = ?1")
    int deleteMovieById(Integer id);

    @Modifying
    @Query("SELECT wm.id.word FROM Movie__wordEntity wm WHERE wm.id.movie.id = :movieId")
    List<WordEntity> findWordsByMovieId(@Param("movieId") Integer movieId);

}
