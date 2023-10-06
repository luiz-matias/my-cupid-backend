package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.UserTemporaryTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTemporaryTokenJpaRepository extends JpaRepository<UserTemporaryTokenEntity, Long> {

    @Query("SELECT p from UserTemporaryTokenEntity p where p.token = :token")
    Optional<UserTemporaryTokenEntity> findByToken(@Param("token") String token);

}
