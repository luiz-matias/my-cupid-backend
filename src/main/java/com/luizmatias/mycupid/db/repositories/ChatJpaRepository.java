package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.db.models.ChatEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatJpaRepository extends JpaRepository<ChatEntity, Long> {

    @Query("SELECT chat FROM ChatEntity chat WHERE chat.firstUserEntity.id = :id OR chat.secondUserEntity.id = :id")
    Page<ChatEntity> findByUserId(@Param("id") Long userId, Pageable pageable);

}
