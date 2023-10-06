package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.PasswordTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenJpaRepository extends JpaRepository<PasswordTokenEntity, Long> {

    @Query("SELECT p from PasswordTokenEntity p where p.token = :token")
    Optional<PasswordTokenEntity> findByToken(@Param("token") String token);

}
