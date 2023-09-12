package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
