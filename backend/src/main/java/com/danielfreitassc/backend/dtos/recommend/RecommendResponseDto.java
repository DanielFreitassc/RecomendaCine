package com.danielfreitassc.backend.dtos.recommend;

import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.user.UserResponseDto;

public record RecommendResponseDto(
    Long id,
    MediaResponseDto media,
    UserResponseDto user
) {
    
}
