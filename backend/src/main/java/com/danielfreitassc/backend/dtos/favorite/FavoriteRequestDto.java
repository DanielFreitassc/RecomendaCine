package com.danielfreitassc.backend.dtos.favorite;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record FavoriteRequestDto(
    @NotNull(message="Necessário o id da mídia!")
    Long mediaId,
    @NotNull(message="Necessário o id do usuário!")
    UUID userId
) {
    
}
