package com.danielfreitassc.backend.dtos.favorite;

import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.user.UserResponseDto;

public record FavoriteResponseDto(
    Long id,
    MediaResponseDto media,
    UserResponseDto user
) {
    
}
