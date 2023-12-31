package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.db.models.UserTemporaryTokenEntity;
import com.luizmatias.mycupid.domain.entities.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTemporaryTokenJpaRepository extends JpaRepository<UserTemporaryTokenEntity, Long> {

    @Query("SELECT p FROM UserTemporaryTokenEntity p WHERE p.token = :token AND p.tokenType = :type")
    Optional<UserTemporaryTokenEntity> findByTokenAndType(@Param("token") String token, @Param("type") TokenType type);

}
