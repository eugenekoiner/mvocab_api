package mvocab_api.repository;

import mvocab_api.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WordRepository extends JpaRepository <WordEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE WordEntity w SET w.word = :#{#word.word}, w.transcription = :#{#word.transcription} WHERE w.id = :id")
    int updateWordById(@Param("id") Integer id, @Param("word") WordEntity wordEntity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM WordEntity f where f.id = ?1")
    int deleteWordById(Integer id);
}
