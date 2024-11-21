package mvocab_api.repository;

import mvocab_api.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository <WordEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE WordEntity w SET w.word = :#{#word.word}, w.transcription = :#{#word.transcription} WHERE w.id = :id")
    int updateWordById(@Param("id") Integer id, @Param("word") WordEntity wordEntity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM WordEntity f where f.id = ?1")
    int deleteWordById(Integer id);

    @Query("SELECT w.id FROM WordEntity w WHERE w.word = :word")
    Optional<Integer> findIdByWord(@Param("word") String word);

    @Query("SELECT w FROM WordEntity w LEFT JOIN FETCH w.translation WHERE w.word IN :words")
    List<WordEntity> findWordEntitiesByIds(@Param("words") List<String> words);
}
