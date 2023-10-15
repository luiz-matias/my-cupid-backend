package com.luizmatias.mycupid.db.models.mappers;

import com.luizmatias.mycupid.db.models.UserTemporaryTokenEntity;
import com.luizmatias.mycupid.domain.entities.UserTemporaryToken;

public class UserTemporaryTokenEntityMapper {

    public static UserTemporaryToken toUserTemporaryToken(UserTemporaryTokenEntity userTemporaryTokenEntity) {
        return new UserTemporaryToken(
                userTemporaryTokenEntity.getId(),
                UserEntityMapper.toUser(userTemporaryTokenEntity.getUserEntity()),
                userTemporaryTokenEntity.getToken(),
                userTemporaryTokenEntity.getTokenType(),
                userTemporaryTokenEntity.getExpiresAt()
        );
    }

    public static UserTemporaryTokenEntity toUserTemporaryTokenEntity(UserTemporaryToken userTemporaryToken) {
        return new UserTemporaryTokenEntity(
                userTemporaryToken.getId(),
                UserEntityMapper.toUserEntity(userTemporaryToken.getUser()),
                userTemporaryToken.getToken(),
                userTemporaryToken.getTokenType(),
                userTemporaryToken.getExpiresAt()
        );
    }

}

