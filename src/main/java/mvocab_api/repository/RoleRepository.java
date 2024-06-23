package mvocab_api.repository;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.RoleEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String roleName);

}