package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.db.models.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {
}
