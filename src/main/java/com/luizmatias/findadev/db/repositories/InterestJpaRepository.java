package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.InterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestJpaRepository extends JpaRepository<InterestEntity, Long> {
}
