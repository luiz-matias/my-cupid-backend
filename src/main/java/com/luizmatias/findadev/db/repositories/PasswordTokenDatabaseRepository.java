package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.PasswordTokenEntity;
import com.luizmatias.findadev.db.models.mappers.PasswordTokenEntityMapper;
import com.luizmatias.findadev.domain.entities.PasswordToken;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.PasswordTokenRepository;

import java.util.Optional;

public class PasswordTokenDatabaseRepository implements PasswordTokenRepository {

    private final PasswordTokenJpaRepository passwordTokenJpaRepository;

    public PasswordTokenDatabaseRepository(PasswordTokenJpaRepository passwordTokenJpaRepository) {
        this.passwordTokenJpaRepository = passwordTokenJpaRepository;
    }

    @Override
    public PasswordToken createPasswordToken(PasswordToken passwordToken) {
        PasswordTokenEntity passwordTokenEntity = PasswordTokenEntityMapper.toPasswordTokenEntity(passwordToken);
        return PasswordTokenEntityMapper.toPasswordToken(passwordTokenJpaRepository.save(passwordTokenEntity));
    }

    @Override
    public PasswordToken getPasswordToken(Long id) throws ResourceNotFoundException {
        Optional<PasswordToken> passwordToken = passwordTokenJpaRepository.findById(id).map(PasswordTokenEntityMapper::toPasswordToken);

        if (passwordToken.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an password token with id = " + id);
        }

        return passwordToken.get();
    }

    @Override
    public Optional<PasswordToken> getPasswordTokenByToken(String token) {
        return passwordTokenJpaRepository.findByToken(token).map(PasswordTokenEntityMapper::toPasswordToken);
    }

    @Override
    public void deletePasswordToken(PasswordToken passwordToken) throws ResourceNotFoundException {
        if (!passwordTokenJpaRepository.existsById(passwordToken.getId())) {
            throw new ResourceNotFoundException("Could not find an password token with id = " + passwordToken.getId());
        }

        passwordTokenJpaRepository.deleteById(passwordToken.getId());
    }
}
