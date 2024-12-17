package com.danielfreitassc.backend.dtos.disliked;

import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.user.UserResponseDto;

public record DislikedResponseDto(
    Long id,
    MediaResponseDto media,
    UserResponseDto user
) {
    
}
