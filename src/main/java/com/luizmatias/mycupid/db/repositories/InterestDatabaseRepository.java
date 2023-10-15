package com.luizmatias.mycupid.db.repositories;

import com.luizmatias.mycupid.api.dtos.mappers.pagination.PageMapper;
import com.luizmatias.mycupid.db.models.InterestEntity;
import com.luizmatias.mycupid.db.models.mappers.InterestEntityMapper;
import com.luizmatias.mycupid.domain.entities.Interest;
import com.luizmatias.mycupid.domain.entities.pagination.PageRequest;
import com.luizmatias.mycupid.domain.entities.pagination.PageResponse;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class InterestDatabaseRepository implements InterestRepository {

    private final InterestJpaRepository interestJpaRepository;

    public InterestDatabaseRepository(InterestJpaRepository interestJpaRepository) {
        this.interestJpaRepository = interestJpaRepository;
    }

    @Override
    public Interest createInterest(Interest interest) {
        InterestEntity interestEntity = InterestEntityMapper.toInterestEntity(interest);
        return InterestEntityMapper.toInterest(interestJpaRepository.save(interestEntity));
    }

    @Override
    public Interest getInterestById(Long id) throws ResourceNotFoundException {
        Optional<Interest> interest = interestJpaRepository.findById(id).map(InterestEntityMapper::toInterest);

        if (interest.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an interest with id = " + id);
        }

        return interest.get();
    }

    @Override
    public PageResponse<Interest> getAllInterests(PageRequest pageRequest) {
        return PageMapper.toPageResponse(
                interestJpaRepository
                        .findAll(PageMapper.toJpaPageRequest(pageRequest).withSort(Sort.by(Sort.Direction.ASC, "id")))
                        .map(InterestEntityMapper::toInterest)
        );
    }

    @Transactional
    @Override
    public Interest updateInterest(Interest interest) throws ResourceNotFoundException {
        Optional<InterestEntity> existingEntityOptional = interestJpaRepository.findById(interest.getId());

        if (existingEntityOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find an interest with id = " + interest.getId());
        }

        InterestEntity interestEntity = existingEntityOptional.get();
        InterestEntity interestEntityUpdated = InterestEntityMapper.toInterestEntity(interest);

        if (interestEntityUpdated.getName() != null) {
            interestEntity.setName(interestEntityUpdated.getName());
        }

        if (interestEntityUpdated.getDescription() != null) {
            interestEntity.setDescription(interestEntityUpdated.getDescription());
        }

        return InterestEntityMapper.toInterest(interestJpaRepository.save(interestEntity));
    }

    @Override
    public void deleteInterest(Interest interest) throws ResourceNotFoundException {
        if (!interestJpaRepository.existsById(interest.getId())) {
            throw new ResourceNotFoundException("Could not find an interest with id = " + interest.getId());
        }

        interestJpaRepository.deleteById(interest.getId());
    }
}
