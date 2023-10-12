package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT m FROM MessageEntity m WHERE m.chatEntity.id = :id ORDER BY m.sentAt ASC")
    Page<MessageEntity> findByChatId(@Param("id") Long chatId, Pageable pageable);

}
