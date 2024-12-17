package com.danielfreitassc.backend.repositories.recommend;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.media.MediaEntity;
import com.danielfreitassc.backend.models.recommend.RecommendEntity;
import com.danielfreitassc.backend.models.user.UserEntity;

public interface RecommendRepository extends JpaRepository<RecommendEntity, Long>{
    Optional<RecommendEntity> findByUserAndMedia(UserEntity user, MediaEntity media);
    List<RecommendEntity> findByUser_Id(UUID id);
}
