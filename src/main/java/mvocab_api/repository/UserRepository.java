package mvocab_api.repository;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserEntity u SET u.email = :#{#user.email}, u.name = :#{#user.name}, u.phone = :#{#user.phone} WHERE u.id = :id")
    int updateUserById(@Param("id") Integer id, @Param("user") UserEntity userEntity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserEntity f where f.id = ?1")
    int deleteUserById(Integer id);

    Optional<UserEntity> findByEmail (String email);

    @Modifying
    @Query("SELECT ul.id.lang FROM User__langEntity ul WHERE ul.id.user.id = :userId")
    List<LangEntity> findLangsByUserId(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user__lang (user_id, lang_id) VALUES (?1, ?2)", nativeQuery = true)
    void addLangByUserId(Integer userId, Integer langId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user__lang WHERE user_id = ?1 AND lang_id = ?2", nativeQuery = true)
    int deleteLangByUserId(Integer userId, Integer langId);

    @Modifying
    @Query("SELECT uw.id.word FROM User__wordEntity uw WHERE uw.id.user.id = :userId")
    List<WordEntity> findWordsByUserId(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user__word (user_id, word_id) VALUES (?1, ?2)", nativeQuery = true)
    void addWordByUserId(Integer userId, Integer wordId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user__word WHERE user_id = ?1 AND word_id = ?2", nativeQuery = true)
    int deleteWordByUserId(Integer userId, Integer wordId);
}