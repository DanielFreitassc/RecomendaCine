package com.danielfreitassc.backend.repositories.media;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.media.MediaEntity;
import com.danielfreitassc.backend.models.media.MediaTypeEnum;

public interface MediaRepository extends JpaRepository<MediaEntity,Long> {
    
    List<MediaEntity> findByGenreInAndMediaType(List<String> genres, MediaTypeEnum mediaType);
}
