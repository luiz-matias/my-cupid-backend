package com.luizmatias.findadev.db.repositories;

import com.luizmatias.findadev.db.models.UserEntity;
import com.luizmatias.findadev.db.models.mappers.UserEntityMapper;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
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
    public User getUser(Long id) throws ResourceNotFoundException {
        Optional<User> user = userJpaRepository.findById(id).map(UserEntityMapper::toUser);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an user with id = " + id);
        }

        return user.get();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserEntityMapper::toUser);
    }

    @Transactional
    @Override
    public User updateUserProfile(User user) throws ResourceNotFoundException {
        Optional<UserEntity> existingUserOptional = userJpaRepository.findById(user.getId());

        if (existingUserOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an user with id = " + user.getId());
        }

        UserEntity userEntity = existingUserOptional.get();
        UserEntity userEntityUpdated = UserEntityMapper.toUserEntity(user);

        if (userEntityUpdated.getFirstName() != null) {
            userEntity.setFirstName(userEntityUpdated.getFirstName());
        }

        if (userEntityUpdated.getLastName() != null) {
            userEntity.setLastName(userEntityUpdated.getLastName());
        }

        if (userEntityUpdated.getBio() != null) {
            userEntity.setBio(userEntityUpdated.getBio());
        }

        if (userEntityUpdated.getBirth() != null) {
            userEntity.setBirth(userEntityUpdated.getBirth());
        }

        if (userEntityUpdated.getEmail() != null) {
            userEntity.setEmail(userEntityUpdated.getEmail());
        }

        if (userEntityUpdated.getLatitude() != null) {
            userEntity.setLatitude(userEntityUpdated.getLatitude());
        }

        if (userEntityUpdated.getLongitude() != null) {
            userEntity.setLongitude(userEntityUpdated.getLongitude());
        }

        return UserEntityMapper.toUser(userJpaRepository.save(userEntity));
    }

    @Transactional
    @Override
    public User updateUserLikes(User user) throws ResourceNotFoundException {
        Optional<UserEntity> existingUserOptional = userJpaRepository.findById(user.getId());

        if (existingUserOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an user with id = " + user.getId());
        }

        UserEntity userEntity = existingUserOptional.get();
        UserEntity userEntityUpdated = UserEntityMapper.toUserEntity(user);

        userEntity.setLikedUserEntities(new ArrayList<>(userEntityUpdated.getLikedUserEntities()));

        return UserEntityMapper.toUser(userJpaRepository.save(userEntity));
    }

    @Override
    public void deleteUser(User user) throws ResourceNotFoundException {
        if (!userJpaRepository.existsById(user.getId())) {
            throw new ResourceNotFoundException("Could not find an user with id = " + user.getId());
        }

        userJpaRepository.deleteById(user.getId());
    }

}
