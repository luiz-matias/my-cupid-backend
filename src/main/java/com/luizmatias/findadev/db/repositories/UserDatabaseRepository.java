package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.UserEntity;
import com.luizmatias.findadev.db.models.mappers.UserEntityMapper;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserDatabaseRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserDatabaseRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntityMapper.toUserEntity(user);
        return UserEntityMapper.toUser(userJpaRepository.save(userEntity));
    }

    @Override
    public List<User> getAllUsers() {
        return userJpaRepository.findAll().stream().map(UserEntityMapper::toUser).toList();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userJpaRepository.findById(id).map(UserEntityMapper::toUser);
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = UserEntityMapper.toUserEntity(user);
        return UserEntityMapper.toUser(userJpaRepository.save(userEntity));
    }

    @Override
    public void deleteUser(User user) {
        userJpaRepository.deleteById(user.getId());
    }

}
