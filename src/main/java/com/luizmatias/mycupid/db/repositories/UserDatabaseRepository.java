package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.mycupid.db.models.UserEntity;
import com.luizmatias.mycupid.db.models.mappers.UserEntityMapper;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Optional;

public class UserDatabaseRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserDatabaseRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntityMapper.toUserEntity(user);
        userEntity.setIsEmailVerified(false);
        return UserEntityMapper.toUser(userJpaRepository.save(userEntity));
    }

    @Override
    public PageResponse<User> getAllUsers(PageRequest pageRequest) {
        return PageMapper.toPageResponse(
                userJpaRepository
                        .findAll(PageMapper.toJpaPageRequest(pageRequest).withSort(Sort.by(Sort.Direction.DESC, "id")))
                        .map(UserEntityMapper::toUser)
        );
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

        if (userEntityUpdated.getLatitude() != null) {
            userEntity.setLatitude(userEntityUpdated.getLatitude());
        }

        if (userEntityUpdated.getLongitude() != null) {
            userEntity.setLongitude(userEntityUpdated.getLongitude());
        }

        if (userEntityUpdated.getInterestEntities() != null && !userEntityUpdated.getInterestEntities().isEmpty()) {
            userEntity.setInterestEntities(new ArrayList<>(userEntityUpdated.getInterestEntities()));
        }

        return UserEntityMapper.toUser(userJpaRepository.save(userEntity));
    }

    @Override
    public User verifyUserEmail(User user) throws ResourceNotFoundException {
        Optional<UserEntity> existingUserOptional = userJpaRepository.findById(user.getId());

        if (existingUserOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an user with id = " + user.getId());
        }

        UserEntity userEntity = existingUserOptional.get();
        userEntity.setIsEmailVerified(true);

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
    public User updateUserPassword(Long id, String password) throws ResourceNotFoundException {
        Optional<UserEntity> existingUserOptional = userJpaRepository.findById(id);

        if (existingUserOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an user with id = " + id);
        }

        UserEntity userEntity = existingUserOptional.get();
        userEntity.setPassword(password);

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
