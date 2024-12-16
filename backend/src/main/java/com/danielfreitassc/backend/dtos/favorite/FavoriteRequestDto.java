package com.danielfreitassc.backend.dtos.favorite;

import java.util.UUID;

public record FavoriteRequestDto(
    Long mediaId,
    UUID userId
) {
    
}
