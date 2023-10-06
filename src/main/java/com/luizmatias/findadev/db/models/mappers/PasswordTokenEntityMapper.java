package com.luizmatias.findadev.db.models.mappers;

import com.luizmatias.findadev.db.models.PasswordTokenEntity;
import com.luizmatias.findadev.db.models.UserEntity;
import com.luizmatias.findadev.domain.entities.PasswordToken;
import com.luizmatias.findadev.domain.entities.User;

import java.util.Collections;

public class PasswordTokenEntityMapper {

    public static PasswordToken toPasswordToken(PasswordTokenEntity passwordTokenEntity) {
        return new PasswordToken(
                passwordTokenEntity.getId(),
                UserEntityMapper.toUser(passwordTokenEntity.getUserEntity()),
                passwordTokenEntity.getToken(),
                passwordTokenEntity.getExpiresAt()
        );
    }

    public static PasswordTokenEntity toPasswordTokenEntity(PasswordToken passwordToken) {
        return new PasswordTokenEntity(
                passwordToken.getId(),
                UserEntityMapper.toUserEntity(passwordToken.getUser()),
                passwordToken.getToken(),
                passwordToken.getExpiresAt()
        );
    }

}

