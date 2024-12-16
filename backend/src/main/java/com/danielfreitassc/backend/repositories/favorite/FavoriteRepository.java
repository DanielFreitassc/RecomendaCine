package com.danielfreitassc.backend.repositories.favorite;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.favorite.FavoriteEntity;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long>{
    Optional<FavoriteEntity> findByUser_Id(UUID id);
}
