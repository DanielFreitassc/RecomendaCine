package com.danielfreitassc.backend.repositories.favorite;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.favorite.FavoriteEntity;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long>{
    List<FavoriteEntity> findByUser_Id(UUID id);
    boolean existsByUser_IdAndMedia_Id(UUID userId, Long mediaId);
    Optional<FavoriteEntity> findByUser_IdAndMedia_Id(UUID userId, Long mediaId);

}
