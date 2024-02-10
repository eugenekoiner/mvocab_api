package mvocab_api.repository;

import mvocab_api.entity.LangEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangRepository extends JpaRepository<LangEntity, Integer> {
}
