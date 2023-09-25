package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u from UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    UserDetails findUserDetailsByEmail(String email);

}
