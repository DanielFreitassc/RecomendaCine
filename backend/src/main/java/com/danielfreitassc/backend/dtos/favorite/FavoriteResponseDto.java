package com.danielfreitassc.backend.dtos.favorite;

import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.user.UserResponseDto;

import jakarta.persistence.ManyToOne;

public record FavoriteResponseDto(
    Long id,
    @ManyToOne
    MediaResponseDto media,
    @ManyToOne
    UserResponseDto user
) {
    
}
