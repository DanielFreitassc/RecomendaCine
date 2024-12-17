package com.danielfreitassc.backend.repositories.disliked;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.disliked.DislikedEntity;

public interface DislikedRepository extends JpaRepository<DislikedEntity, Long>{
    List<DislikedEntity> findByUser_Id(UUID id);
    boolean existsByUser_IdAndMedia_Id(UUID userId, Long mediaId);
    Optional<DislikedEntity> findByUser_IdAndMedia_Id(UUID userId, Long mediaId);
}
