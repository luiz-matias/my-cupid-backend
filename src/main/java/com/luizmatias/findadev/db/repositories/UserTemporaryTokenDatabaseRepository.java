package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.UserTemporaryTokenEntity;
import com.luizmatias.findadev.db.models.mappers.UserTemporaryTokenEntityMapper;
import com.luizmatias.findadev.domain.entities.TokenType;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserTemporaryTokenRepository;

import java.util.Optional;

public class UserTemporaryTokenDatabaseRepository implements UserTemporaryTokenRepository {

    private final UserTemporaryTokenJpaRepository userTemporaryTokenJpaRepository;

    public UserTemporaryTokenDatabaseRepository(UserTemporaryTokenJpaRepository userTemporaryTokenJpaRepository) {
        this.userTemporaryTokenJpaRepository = userTemporaryTokenJpaRepository;
    }

    @Override
    public UserTemporaryToken createTemporaryToken(UserTemporaryToken userTemporaryToken) {
        UserTemporaryTokenEntity userTemporaryTokenEntity = UserTemporaryTokenEntityMapper.toUserTemporaryTokenEntity(userTemporaryToken);
        return UserTemporaryTokenEntityMapper.toUserTemporaryToken(userTemporaryTokenJpaRepository.save(userTemporaryTokenEntity));
    }

    @Override
    public UserTemporaryToken getTemporaryToken(Long id) throws ResourceNotFoundException {
        Optional<UserTemporaryToken> passwordToken = userTemporaryTokenJpaRepository.findById(id).map(UserTemporaryTokenEntityMapper::toUserTemporaryToken);

        if (passwordToken.isEmpty()) {
            throw new ResourceNotFoundException("Could not find a token with id = " + id);
        }

        return passwordToken.get();
    }

    @Override
    public Optional<UserTemporaryToken> getTemporaryTokenByTokenAndType(String token, TokenType type) {
        return userTemporaryTokenJpaRepository.findByTokenAndType(token, type).map(UserTemporaryTokenEntityMapper::toUserTemporaryToken);
    }

    @Override
    public void deleteTemporaryToken(UserTemporaryToken userTemporaryToken) throws ResourceNotFoundException {
        if (!userTemporaryTokenJpaRepository.existsById(userTemporaryToken.getId())) {
            throw new ResourceNotFoundException("Could not find a token with id = " + userTemporaryToken.getId());
        }

        userTemporaryTokenJpaRepository.deleteById(userTemporaryToken.getId());
    }
}
