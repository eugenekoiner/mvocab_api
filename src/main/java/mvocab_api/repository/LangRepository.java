package mvocab_api.repository;

import mvocab_api.entity.LangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LangRepository extends JpaRepository<LangEntity, Integer> {
    @Query("SELECT l FROM LangEntity l JOIN WordEntity w ON l.id = w.lang_id WHERE w.id = :wordId")
    LangEntity findLangByWordId(@Param("wordId") Integer wordId);
}
