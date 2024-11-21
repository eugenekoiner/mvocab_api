package mvocab_api.repository;

import mvocab_api.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TranslationRepository extends JpaRepository<TranslationEntity, Integer> {

    @Query("SELECT t FROM TranslationEntity t JOIN t.word w WHERE w.lang_id = :langId AND w.id = :wordId")
    Optional<TranslationEntity> findTranslationByLangIdAndWordId(@Param("langId") Integer langId, @Param("wordId") Integer wordId);

    @Transactional
    @Modifying
    @Query("UPDATE TranslationEntity t SET t.translation = :translation WHERE t.id = :id")
    int updateTranslationById(@Param("id") Integer id, @Param("translation") String translation);

    @Transactional
    @Modifying
    @Query("UPDATE TranslationEntity t SET t.translation = :translation WHERE t.word.id = :wordId AND t.word.lang_id = :langId")
    int updateTranslationByLangIdAndWordId(@Param("translation") String translation, @Param("langId") Integer langId, @Param("wordId") Integer wordId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO translation (translation, word_id) VALUES (:translation, :wordId)", nativeQuery = true)
    int insertTranslation(@Param("translation") String translation, @Param("wordId") Integer wordId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM TranslationEntity t where t.id = ?1")
    int deleteTranslationById(Integer id);
}
