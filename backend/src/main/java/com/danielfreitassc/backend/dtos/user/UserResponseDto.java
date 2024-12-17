package com.danielfreitassc.backend.dtos.user;

import java.util.List;
import java.util.UUID;

import com.danielfreitassc.backend.models.media.MediaTypeEnum;

public record  UserResponseDto(
    UUID id,
    String name,
    String image,
    String email,
    MediaTypeEnum favoriteMediaType,
    List<String> favoriteGenre
) {
    
}