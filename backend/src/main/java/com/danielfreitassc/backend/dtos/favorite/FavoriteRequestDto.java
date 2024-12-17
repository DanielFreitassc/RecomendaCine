package com.danielfreitassc.backend.dtos.favorite;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record FavoriteRequestDto(
    @NotNull
    Long mediaId,
    @NotNull
    UUID userId
) {
    
}
