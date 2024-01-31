package mvocab_api.repository;

import mvocab_api.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository <WordEntity, Integer> {
}
