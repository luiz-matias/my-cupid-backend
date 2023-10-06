package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.UserTemporaryTokenEntity;
import com.luizmatias.findadev.domain.entities.UserTemporaryToken;

public class UserTemporaryTokenEntityMapper {

    public static UserTemporaryToken toUserTemporaryToken(UserTemporaryTokenEntity userTemporaryTokenEntity) {
        return new UserTemporaryToken(
                userTemporaryTokenEntity.getId(),
                UserEntityMapper.toUser(userTemporaryTokenEntity.getUserEntity()),
                userTemporaryTokenEntity.getToken(),
                userTemporaryTokenEntity.getExpiresAt()
        );
    }

    public static UserTemporaryTokenEntity toUserTemporaryTokenEntity(UserTemporaryToken userTemporaryToken) {
        return new UserTemporaryTokenEntity(
                userTemporaryToken.getId(),
                UserEntityMapper.toUserEntity(userTemporaryToken.getUser()),
                userTemporaryToken.getToken(),
                userTemporaryToken.getExpiresAt()
        );
    }

}

