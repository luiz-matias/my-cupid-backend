package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
}
