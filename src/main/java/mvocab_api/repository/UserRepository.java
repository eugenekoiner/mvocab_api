package mvocab_api.repository;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserEntity f where f.id = ?1")
    int deleteUserById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserEntity u SET u.email = :#{#user.email}, u.name = :#{#user.name}, u.phone = :#{#user.phone} WHERE u.id = :id")
    int updateUserById(@Param("id") Integer id, @Param("user") UserEntity userEntity);

    UserEntity findByEmail (String email);

    @Modifying
    @Query("SELECT ul.id.lang FROM User__langEntity ul WHERE ul.id.user.id = :userId")
    List<LangEntity> findLangsByUserId(@Param("userId") Integer userId);

}