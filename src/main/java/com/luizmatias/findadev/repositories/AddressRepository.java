package com.luizmatias.findadev.repositories;

import com.luizmatias.findadev.domain.address.Address;
import com.luizmatias.findadev.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
